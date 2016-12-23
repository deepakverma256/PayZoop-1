package com.objectedge.payzoop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deepak.verma on 22-08-2016.
 */
public class GetAllCategoriesListModel {
    @SerializedName("childCategories")
    List<CategoryModel> childCategories;

    public List<CategoryModel> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryModel> childCategories) {
        this.childCategories = childCategories;
    }
}
