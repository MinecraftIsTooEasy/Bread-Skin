package cn.xylose.mitemod.breadskin.config;

import cn.xylose.mitemod.breadskin.BreadSkin;

import java.util.function.Function;

public enum EnumNutritionInfoMode {
    Empty(integer -> {
        throw new IllegalCallerException();
    }),
    Exact(integer -> integer + "/" + BreadSkin.nutritionLimit),
    Percentage(integer -> (integer * 100 / BreadSkin.nutritionLimit) + "%"),
    Mixed(integer -> integer + "/" + BreadSkin.nutritionLimit + " (" + (int) (integer * 100 / BreadSkin.nutritionLimit) + "%)"),
    ;

    public final Function<Integer, String> formatter;

    EnumNutritionInfoMode(Function<Integer, String> formatter) {
        this.formatter = formatter;
    }
}
