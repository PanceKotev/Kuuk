package com.mpip.kuuk.dto;

import java.util.List;
import java.util.Map;

public class RecipeDto  {
    private Long RecipeId;
    private int cookingTime;
    private String cuisine;
    private String imgUrl;
    private String name;
    private int servings;
    private boolean vegan;
    private String ingredientNmbr;
    private String ingredientNames;
    private Map<String,String> steps;
    private String category;

    public RecipeDto(Long recipeId, int cookingTime, String cuisine, String imgUrl, String name, int servings, boolean vegan,String ingredientNames,String ingredientNmbr, Map<String,String> steps,String category) {
        RecipeId = recipeId;
        this.cookingTime = cookingTime;
        this.cuisine = cuisine;
        this.imgUrl = imgUrl;
        this.name = name;
        this.servings = servings;
        this.vegan = vegan;
        this.ingredientNames=ingredientNames;
        this.ingredientNmbr=ingredientNmbr;
        this.steps = steps;
        this.category = category;
    }

    public RecipeDto() {
    }

    public Long getRecipeId() {
        return RecipeId;
    }

    public void setRecipeId(Long recipeId) {
        RecipeId = recipeId;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public Map<String,String> getSteps() {
        return steps;
    }

    public void setSteps(Map<String,String> steps) {
        this.steps = steps;
    }
    public String getIngredientNames() {
        return ingredientNames;
    }

    public void setIngredientNames(String ingredientNames) {
        this.ingredientNames = ingredientNames;
    }
    public String getIngredientNmbr() {
        return ingredientNmbr;
    }

    public void setIngredientNmbr(String ingredientNmbr) {
        this.ingredientNmbr = ingredientNmbr;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
