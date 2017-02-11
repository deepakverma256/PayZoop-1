package com.objectedge.restroappproto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak.verma on 11-02-2017.
 */
public class MenuModel {
    public List<SubMenuModel> getSubMenus() {
        return subMenuModels;
    }

    public void setSubMenus(List<com.objectedge.restroappproto.model.SubMenuModel> subMenuModels) {
        this.subMenuModels = subMenuModels;
    }

    List<com.objectedge.restroappproto.model.SubMenuModel> subMenuModels;


    public static MenuModel getDummyMenu(){
        MenuModel menu = new MenuModel();
        menu.setSubMenus(new ArrayList<SubMenuModel>());
        menu.getSubMenus().add(SubMenuModel.getDummySubMenu());
        menu.getSubMenus().add(SubMenuModel.getDummySubMenu());
        menu.getSubMenus().add(SubMenuModel.getDummySubMenu());
        return menu;
    }
}
