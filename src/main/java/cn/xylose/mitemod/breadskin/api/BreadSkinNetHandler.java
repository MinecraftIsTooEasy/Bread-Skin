package cn.xylose.mitemod.breadskin.api;

import cn.xylose.mitemod.breadskin.network.S2CUpdateNutrition;

public interface BreadSkinNetHandler {
    default void breadSkin$HandleUpdateNutrition(S2CUpdateNutrition packet) {
    }
}
