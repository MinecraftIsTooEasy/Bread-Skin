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
    public static final ConfigEnum<EnumNutritionBarMode> NutritionBarMode = new ConfigEnum<>("营养条模式", EnumNutritionBarMode.Mixed);
    public static final ConfigBoolean ExactNutrition = new ConfigBoolean("具体营养值");
    public static final ConfigBoolean SecondaryDecrement = new ConfigBoolean("二次递减", true, "ITF同款, 在营养接近满时更容易察觉变化");
    public static final ConfigInteger LeftBarOffset = new ConfigInteger("左营养条位置偏移", 0, -512, 512);
    public static final ConfigInteger RightBarOffset = new ConfigInteger("右营养条位置偏移", 0, -512, 512);
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
        NutritionBar = List.of(DrawNutritionBar, NutritionBarMode, ExactNutrition, SecondaryDecrement, LeftBarOffset, RightBarOffset);

        Total.addAll(BreadSkin);
        Total.addAll(NutritionBar);

        tabs.add(new ConfigTab("面包皮", BreadSkin));
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
