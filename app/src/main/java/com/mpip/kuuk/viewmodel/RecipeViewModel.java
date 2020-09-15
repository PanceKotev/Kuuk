package com.mpip.kuuk.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.dto.RecipeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeViewModel extends ViewModel {
    private MutableLiveData<List<RecipeDto>> recipes = new MutableLiveData();
    private String searcher=null;
    private boolean changedSearch=false;

    public LiveData<List<RecipeDto>> getRecipes(){

        if (recipes.getValue()==null || changedSearch){
            FirebaseDatabase.getInstance()
                    .getReference("/recipes").orderByChild("ingredientNames").equalTo(searcher)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                recipes.postValue(toRecipes(snapshot));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        return recipes;
    }
    List<RecipeDto> toRecipes(DataSnapshot snapshot){
        List<RecipeDto> transformed=new ArrayList<RecipeDto>();
        for (DataSnapshot recipe:snapshot.getChildren()){
            transformed.add(recipe.getValue(RecipeDto.class));

        }
        return transformed;
    }
    public void setIngredients(List<String> ingredients){
        Collections.sort(ingredients);
        String newSearcher= String.join("_",ingredients);
        if(searcher==null){
            searcher=newSearcher;
        }
        else if(!searcher.equals(newSearcher)){
            searcher=newSearcher;
            changedSearch=true;
        }
        else{
            changedSearch=false;
        }
    }
    public String getSearcher(){
        return this.searcher;
    }
}
