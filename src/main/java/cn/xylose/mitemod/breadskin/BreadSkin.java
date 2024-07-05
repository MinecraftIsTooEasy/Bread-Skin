package cn.xylose.mitemod.breadskin;

import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class BreadSkin implements ModInitializer {
    public static int nutritionLimit = 160000;
    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new BreadSkinEvent());
        BreadSkinConfigs.getInstance().load();
        if (FishModLoader.hasMod("mite-itf-reborn")) {
            nutritionLimit = 960000;
        }
        ConfigManager.getInstance().registerConfig(BreadSkinConfigs.getInstance());
    }
}
