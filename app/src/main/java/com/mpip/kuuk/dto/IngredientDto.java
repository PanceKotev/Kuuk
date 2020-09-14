package com.mpip.kuuk.dto;

import java.util.UUID;

public class IngredientDto  {
    private long ingredientID;
    private String imgUrl;
    private String name;
    private String region;
    private String type;

    public IngredientDto(long ingredientID, String imgUrl, String name, String region, String type) {
        this.ingredientID = ingredientID;
        this.imgUrl = imgUrl;
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public IngredientDto() {
    }

    public long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(long ingredientID) {
        this.ingredientID = ingredientID;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
