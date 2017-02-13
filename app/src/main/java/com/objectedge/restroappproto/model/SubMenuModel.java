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


    public static SubMenuModel getDummySubMenu1(){
        SubMenuModel submenu = new SubMenuModel();
        submenu.setDishes(new ArrayList<DishModel>());
        submenu.setName("SUBCATEGORY");
        DishModel dish1 = DishModel.getDummyDish1();
        dish1.setId("ABK1");
        submenu.getDishes().add(dish1);
        DishModel dish2 = DishModel.getDummyDish2();
        dish2.setId("ABK2");
        submenu.getDishes().add(dish2);
        DishModel dish3 = DishModel.getDummyDish3();
        dish3.setId("ABK3");
        submenu.getDishes().add(dish3);
        DishModel dish4 = DishModel.getDummyDish4();
        dish4.setId("ABK4");
        submenu.getDishes().add(dish4);
        return submenu;
    }
    public static SubMenuModel getDummySubMenu2(){
        SubMenuModel submenu = new SubMenuModel();
        submenu.setDishes(new ArrayList<DishModel>());
        submenu.setName("SUBCATEGORY");
        DishModel dish1 = DishModel.getDummyDish1();
        dish1.setId("BBK1");
        submenu.getDishes().add(dish1);
        DishModel dish2 = DishModel.getDummyDish2();
        dish2.setId("BBK2");
        submenu.getDishes().add(dish2);
        DishModel dish3 = DishModel.getDummyDish3();
        dish3.setId("BBK3");
        submenu.getDishes().add(dish3);
        DishModel dish4 = DishModel.getDummyDish4();
        dish4.setId("BBK4");
        submenu.getDishes().add(dish4);
        return submenu;
    }
    public static SubMenuModel getDummySubMenu3(){
        SubMenuModel submenu = new SubMenuModel();
        submenu.setDishes(new ArrayList<DishModel>());
        submenu.setName("SUBCATEGORY");
        DishModel dish1 = DishModel.getDummyDish1();
        dish1.setId("CBK1");
        submenu.getDishes().add(dish1);
        DishModel dish2 = DishModel.getDummyDish2();
        dish2.setId("CBK2");
        submenu.getDishes().add(dish2);
        DishModel dish3 = DishModel.getDummyDish3();
        dish3.setId("CBK3");
        submenu.getDishes().add(dish3);
        DishModel dish4 = DishModel.getDummyDish4();
        dish4.setId("CBK4");
        submenu.getDishes().add(dish4);
        return submenu;
    }
}
