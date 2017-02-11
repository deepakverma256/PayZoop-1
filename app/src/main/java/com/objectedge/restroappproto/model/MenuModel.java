package com.objectedge.restroappproto.model;

import java.util.List;

/**
 * Created by deepak.verma on 13-01-2017.
 */
public class MenuModel {
    public List<com.objectedge.restroappproto.model.DishModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<com.objectedge.restroappproto.model.DishModel> dishes) {
        this.dishes = dishes;
    }

    List<com.objectedge.restroappproto.model.DishModel> dishes;
}
