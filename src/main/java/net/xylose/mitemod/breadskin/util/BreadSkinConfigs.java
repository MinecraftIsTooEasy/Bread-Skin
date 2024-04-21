package net.xylose.mitemod.breadskin.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;


public class BreadSkinConfigs extends SimpleConfigs {
//    public static final File CONFIG_FILE = new File("bread-skin-cfg.json");
//    public static final FieldReference<Boolean> Display_Saturation = new FieldReference(true);
//    public static final FieldReference<Integer> Hud_Y = new FieldReference(0);
//    public static final ConfigRoot ROOT = ConfigRoot.create(BreadSkin.CONFIG_VERSION)
//            .addEntry(ConfigEntry.of("display_saturation", Display_Saturation).withComment("是否显示饱和度"))
//            .addEntry(ConfigEntry.of("hud_y", Hud_Y).withComment("饱和度的显示高度,每10单位为一行,增加数值为上升"));

    public static final ConfigBoolean Display_Saturation = new ConfigBoolean("是否显示饱和度", true, "是否显示饱和度");
    public static final ConfigInteger Saturation_Hud_Y = new ConfigInteger("饱和度的显示高度", 0, -30, 200, "每10单位为一行,增加数值为上升");

    private static BreadSkinConfigs Instance;
    public static List<ConfigBase> BreadSkin;

    public BreadSkinConfigs(String name, List<ConfigHotkey> hotkeys, List<ConfigBase> values) {
        super(name, hotkeys, values);
    }

    public static void init() {
        BreadSkin = List.of(Display_Saturation, Saturation_Hud_Y);
        ArrayList<ConfigBase> values = new ArrayList<ConfigBase>();
        values.addAll(BreadSkin);
        Instance = new BreadSkinConfigs("BreadSkin", null, values);
    }

    public static BreadSkinConfigs getInstance() {
        return Instance;
    }

    public void save() {
        JsonObject configRoot = new JsonObject();
        ConfigUtils.writeConfigBase(configRoot, "Values", BreadSkin);
        JsonObject valuesRoot = JsonUtils.getNestedObject(configRoot, "Values", true);
        ConfigUtils.writeConfigBase(valuesRoot, "BreadSkin", BreadSkin);
        JsonUtils.writeJsonToFile(configRoot, this.getOptionsFile());
    }

    public void load() {
        if (!this.getOptionsFile().exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.getOptionsFile());
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject obj = jsonElement.getAsJsonObject();
                ConfigUtils.readConfigBase(obj, "Values", BreadSkin);
                JsonObject valuesRoot = JsonUtils.getNestedObject(obj, "Values", true);
                ConfigUtils.readConfigBase(valuesRoot, "BreadSkin", BreadSkin);
            }
        }
    }

}
