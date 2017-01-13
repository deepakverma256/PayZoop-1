package com.objectedge.payzoop.model;

import java.util.List;

/**
 * Created by deepak.verma on 13-01-2017.
 */
public class MenuModel {
    public List<DishModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishModel> dishes) {
        this.dishes = dishes;
    }

    List<DishModel> dishes;
}
