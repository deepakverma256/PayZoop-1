package com.objectedge.restroappproto.event;

import com.objectedge.restroappproto.model.CategoryModel;

/**
 * Created by deepak.verma on 24-08-2016.
 */
public class CategoryClickEvent extends Event {

    public static class ListProductsEvent extends CategoryClickEvent{
        public CategoryModel category;

        public ListProductsEvent(CategoryModel category){
            this.category = category;
        }
    }

    public static class FreshListingPageEvent extends CategoryClickEvent{
        public CategoryModel category;

        public FreshListingPageEvent(CategoryModel category){
            this.category = category;
        }


    }

}
