package com.mpip.kuuk.viewmodel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mpip.kuuk.dto.IngredientDto;
import com.mpip.kuuk.model.LoadingState;

import java.util.ArrayList;
import java.util.List;

public class IngredientsViewModel extends ViewModel {
    private MutableLiveData<List<IngredientDto>> ingredients = new MutableLiveData();
    private MutableLiveData<LoadingState> state=new MutableLiveData<>();

    public LiveData<List<IngredientDto>> getIngredients(){
        if (ingredients.getValue()==null){
            state.setValue(LoadingState.LOADING);
            FirebaseDatabase.getInstance()
                    .getReference("/ingredients")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                ingredients.postValue(toIngredients(snapshot));
                                state.setValue(LoadingState.LIVE);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        return ingredients;
    }
    public LiveData<LoadingState> getState(){
        return state;
    }
    List<IngredientDto> toIngredients(DataSnapshot snapshot){
        List<IngredientDto> transformed=new ArrayList<IngredientDto>();
        for (DataSnapshot ingredient:snapshot.getChildren()){
            transformed.add(ingredient.getValue(IngredientDto.class));
        }
        return transformed;
    }
}
