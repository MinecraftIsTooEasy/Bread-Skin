package cn.xylose.mitemod.breadskin.config;

import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.*;

import java.util.ArrayList;
import java.util.List;

public class BreadSkinConfigs extends SimpleConfigs {
    public static final ConfigBoolean Display_Saturation = new ConfigBoolean("显示饱和度", true, "饱和度显示总开关");
    public static final ConfigInteger Saturation_Hud_Y = new ConfigInteger("饱和度的显示高度", 0, -30, 200, "每10单位为一行,增加数值为上升(类苹果皮模式无效)");
    public static final ConfigBoolean Apple_Skin_Mode = new ConfigBoolean("类苹果皮模式", false, "在饱食度描边显示饱和度");
    public static final ConfigBoolean DrawNutritionBar = new ConfigBoolean("绘画营养条", true);
    public static final ConfigEnum<EnumNutritionBarMode> NutritionBarMode = new ConfigEnum<>("breadSkin.nutritionBarMode", EnumNutritionBarMode.Mixed);
    public static final ConfigEnum<EnumNutritionInfoMode> NutritionInfoMode = new ConfigEnum<>("breadSkin.nutritionInfoMode", EnumNutritionInfoMode.Mixed);
    public static final ConfigBoolean SecondaryDecrement = new ConfigBoolean("二次递减", true, "ITF同款, 在营养接近满时更容易察觉变化");
    public static final ConfigInteger LeftBarOffset = new ConfigInteger("蛋白质营养条横向位置偏移", 0, -768, 768);
    public static final ConfigInteger RightBarOffset = new ConfigInteger("植物营养素营养条横向位置偏移", 0, -768, 768);
    public static final ConfigInteger BarYOffset = new ConfigInteger("营养条纵向位置偏移", 0, -768, 768);
    public static final ConfigInteger NutritionLimit = new ConfigInteger("营养值上限", 160000, 0, Integer.MAX_VALUE, false, null);
    private static final BreadSkinConfigs Instance;
    public static final List<ConfigBase<?>> BreadSkin;
    public static final List<ConfigBase<?>> NutritionBar;
    public static final List<ConfigBase<?>> Total = new ArrayList<>();
    public static final List<ConfigTab> tabs = new ArrayList<>();

    public BreadSkinConfigs(String name, List<ConfigHotkey> hotkeys, List<ConfigBase<?>> values) {
        super(name, hotkeys, values);
    }

    static {
        BreadSkin = List.of(Display_Saturation, Saturation_Hud_Y, Apple_Skin_Mode);
        NutritionBar = List.of(DrawNutritionBar, NutritionBarMode, NutritionInfoMode, SecondaryDecrement, LeftBarOffset, RightBarOffset, BarYOffset, NutritionLimit);

        Total.addAll(BreadSkin);
        Total.addAll(NutritionBar);

        tabs.add(new ConfigTab("饱和度", BreadSkin));
        tabs.add(new ConfigTab("营养条", NutritionBar));

        Instance = new BreadSkinConfigs("BreadSkin", null, Total);
    }

    @Override
    public List<ConfigTab> getConfigTabs() {
        return tabs;
    }

    public static BreadSkinConfigs getInstance() {
        return Instance;
    }
}
