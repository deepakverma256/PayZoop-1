package com.objectedge.restroappproto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak.verma on 13-01-2017.
 */
public class SubMenuModel {
    public String name;

    public List<com.objectedge.restroappproto.model.DishModel> getDishes() {
        return dishes;
    }

    public void setDishes(List<com.objectedge.restroappproto.model.DishModel> dishes) {
        this.dishes = dishes;
    }

    List<com.objectedge.restroappproto.model.DishModel> dishes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static SubMenuModel getDummySubMenu(){
        SubMenuModel submenu = new SubMenuModel();
        submenu.setDishes(new ArrayList<DishModel>());
        submenu.setName("SUBCATEGORY");
        submenu.getDishes().add(DishModel.getDummyDish());
        submenu.getDishes().add(DishModel.getDummyDish());
        submenu.getDishes().add(DishModel.getDummyDish());
        return submenu;
    }
}
