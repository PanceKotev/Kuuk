package com.mpip.kuuk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mpip.kuuk.adapter.IngredientListAdapter;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.viewmodel.IngredientsViewModel;
import com.mpip.kuuk.viewmodel.RecipeViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private IngredientsViewModel ingredientsViewModel;
    private IngredientListAdapter adapter;
    List<String> ingredientNames= new ArrayList<>();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngredientsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientsFragment newInstance(String param1, String param2) {
        IngredientsFragment fragment = new IngredientsFragment();
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

        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    private View.OnClickListener getItemViewOnClickListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                IngredientListAdapter.ingredientViewHolder holder = (IngredientListAdapter.ingredientViewHolder)v.getTag();
                String ingredientName=adapter.getClickedItemName(holder).toLowerCase();
                if(!ingredientNames.contains(ingredientName)){

                    ingredientNames.add(ingredientName);
                    holder.setItemSelected(true);
                }
                else{
                    ingredientNames.remove(ingredientName);
                    holder.setItemSelected(false);
                }
            };
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context c = this.getContext();
        RecyclerView recyclerView=getView().findViewById(R.id.recycler_ing);
        recyclerView.setLayoutManager(new GridLayoutManager(c,3));
        adapter = new IngredientListAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(getItemViewOnClickListener());
        ingredientsViewModel = new ViewModelProvider(this).get(IngredientsViewModel.class);
        ingredientsViewModel.getIngredients().observe(getViewLifecycleOwner(), new Observer<List<IngredientDto>>() {
            @Override
            public void onChanged(List<IngredientDto> ingredientDtos) {
                adapter.updateDataset(ingredientDtos);
            }
        });

        ExtendedFloatingActionButton floatingActionButton=getView().findViewById(R.id.showIngredients);
        final RecipeViewModel recipeViewModel=new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeViewModel.setIngredients(ingredientNames);
                Navigation.findNavController(v).navigate(R.id.action_ingredientsFragment_to_mainFragment);
            }
        });

    }
}