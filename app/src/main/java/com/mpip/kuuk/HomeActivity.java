package com.mpip.kuuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.mpip.kuuk.viewmodel.RecipeDetailsViewModel;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavController navController;
    FirebaseAuth mAuth;
    RecipeDetailsViewModel recipeDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth= FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(findViewById(R.id.nav_fragment));
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainFragment, R.id.ingredientsFragment).
                setDrawerLayout(drawerLayout).build();
        recipeDetailsViewModel=new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.ingredientsFragment){

                    toolbar.setTitle("Select Ingredients");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
                else if(destination.getId()==R.id.mainFragment){
                    toolbar.setTitle("Recipes");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
                else if(destination.getId()==R.id.detailsFragment){
                    toolbar.setTitle("Details");
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.nav_logout){
            mAuth.signOut();
            Toast.makeText(this,"You've logged out",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LoginActivity.class));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
                drawerLayout.openDrawer(GravityCompat.START);
        }

        return true;
    }
}