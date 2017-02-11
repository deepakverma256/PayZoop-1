package com.objectedge.restroappproto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deepak.verma on 22-08-2016.
 */
public class CategoryModel {
    @SerializedName("repositoryId")
    String repositoryId;
    @SerializedName("childCategories")
    List<ChildCategory> childCategories;
    @SerializedName("id")
    String id;
    @SerializedName("categoryImages")
    List<CategoryImage> categoryImages;
    @SerializedName("route")
    String route;
    @SerializedName("description")
    String description;
    @SerializedName("longDescription")
    String longDescription;
    @SerializedName("active")
    Boolean active;
    @SerializedName("fixedParentCategories")
    List<FixedParentCategory> fixedParentCategories;
    @SerializedName("categoryPaths")
    List<String> categoryPaths;
    @SerializedName("displayName")
    String displayName;

    public List<FixedParentCategory> getFixedParentCategories() {
        return fixedParentCategories;
    }

    public void setFixedParentCategories(List<FixedParentCategory> fixedParentCategories) {
        this.fixedParentCategories = fixedParentCategories;
    }

    public List<ChildCategory> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<ChildCategory> childCategories) {
        this.childCategories = childCategories;
    }

    public List<CategoryImage> getCategoryImages() {
        return categoryImages;
    }

    public void setCategoryImages(List<CategoryImage> categoryImages) {
        this.categoryImages = categoryImages;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<String> getCategoryPaths() {
        return categoryPaths;
    }

    public void setCategoryPaths(List<String> categoryPaths) {
        this.categoryPaths = categoryPaths;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public class ChildCategory{
        @SerializedName("repositoryId")
        String repositoryId;

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }
    }

    public class CategoryImage{
        @SerializedName("repositoryId")
        String repositoryId;

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }
    }

    public class FixedParentCategory{
        @SerializedName("repositoryId")
        String repositoryId;

        public String getRepositoryId() {
            return repositoryId;
        }

        public void setRepositoryId(String repositoryId) {
            this.repositoryId = repositoryId;
        }
    }
}
