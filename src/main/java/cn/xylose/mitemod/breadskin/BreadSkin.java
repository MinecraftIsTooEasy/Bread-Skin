package cn.xylose.mitemod.breadskin;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;

public class BreadSkin implements ModInitializer {
    @Override
    public void onInitialize() {
        BreadSkinConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(BreadSkinConfigs.getInstance());
    }
}
