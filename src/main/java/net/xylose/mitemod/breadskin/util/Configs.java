package net.xylose.mitemod.breadskin.util;

import net.xiaoyu233.fml.config.ConfigCategory;
import net.xiaoyu233.fml.config.ConfigEntry;
import net.xiaoyu233.fml.util.FieldReference;


public class Configs {
    public static final FieldReference<Boolean> Display_Saturation = new FieldReference(true);

    public static final FieldReference<Integer> Hud_Y = new FieldReference(0);

    public static final ConfigCategory ROOT = ConfigCategory.of("Bread Skin")
            .addEntry(ConfigEntry.of("display_saturation", Display_Saturation).withComment("是否显示饱和度"))
            .addEntry(ConfigEntry.of("hud_y", Hud_Y).withComment("饱和度的显示高度,每10单位为一行,增加数值为上升"));
}
