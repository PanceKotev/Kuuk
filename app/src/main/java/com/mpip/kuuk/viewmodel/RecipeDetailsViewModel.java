package com.mpip.kuuk.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mpip.kuuk.dto.RecipeDto;

public class RecipeDetailsViewModel extends ViewModel {
    private MutableLiveData<RecipeDto> recipeDetail=new MutableLiveData<>();

    public LiveData<RecipeDto> getRecipe(){
        return recipeDetail;
    }
    public void setRecipe(RecipeDto recipeDto){
        recipeDetail.setValue(recipeDto);
    }
    public String getRecipeName(){
        return recipeDetail==null?recipeDetail.getValue().getName():"";
    }
}
