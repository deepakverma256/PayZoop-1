package com.objectedge.restroappproto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deepak.verma on 22-08-2016.
 */
public class GetAllCategoriesListModel {
    @SerializedName("childCategories")
    List<com.objectedge.restroappproto.model.CategoryModel> childCategories;

    public List<com.objectedge.restroappproto.model.CategoryModel> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<com.objectedge.restroappproto.model.CategoryModel> childCategories) {
        this.childCategories = childCategories;
    }
}
