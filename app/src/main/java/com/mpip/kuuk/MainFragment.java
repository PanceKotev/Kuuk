package com.mpip.kuuk;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mpip.kuuk.adapter.IngredientListAdapter;
import com.mpip.kuuk.adapter.RecipeListAdapter;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.dto.RecipeDto;
import com.mpip.kuuk.viewmodel.IngredientsViewModel;
import com.mpip.kuuk.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecipeViewModel recipeViewModel;
    private RecipeListAdapter recipeListAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view= inflater.inflate(R.layout.fragment_main, container, false);
        FloatingActionButton actionButton= view.findViewById(R.id.actionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_ingredientsFragment);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=getView().findViewById(R.id.recycler_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeListAdapter = new RecipeListAdapter();
        recyclerView.setAdapter(recipeListAdapter);
        recipeListAdapter.setItemListener(getItemViewOnClickListener());
        recipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        if(recipeViewModel.getSearcher()!=null){
            Log.d("Searcher",recipeViewModel.getSearcher());

        recipeViewModel.getRecipes().observe(getViewLifecycleOwner(), new Observer<List<RecipeDto>>() {
            @Override
            public void onChanged(List<RecipeDto> recipeDtos) {
                recipeListAdapter.updateDataset(recipeDtos);
            }
        });}
    }

    private View.OnClickListener getItemViewOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeListAdapter.RecipeViewHolder holder = (RecipeListAdapter.RecipeViewHolder) v.getTag();
                Toast.makeText(getContext(),recipeListAdapter.getClickedItemName(holder).getName(),Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }




}