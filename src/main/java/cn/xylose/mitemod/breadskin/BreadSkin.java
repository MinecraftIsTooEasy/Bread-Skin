package cn.xylose.mitemod.breadskin;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class BreadSkin implements ModInitializer {

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new BreadSkinEvent());
        BreadSkinConfigs.getInstance().load();
        if (BreadSkinConfigs.NutritionLimit.getIntegerValue() == 160000 && FishModLoader.hasMod("mite-itf-reborn")) {
            BreadSkinConfigs.NutritionLimit.setIntegerValue(960000);
            BreadSkinConfigs.getInstance().save();
        }
        ConfigManager.getInstance().registerConfig(BreadSkinConfigs.getInstance());
    }
}
