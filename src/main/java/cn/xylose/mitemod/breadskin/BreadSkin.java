package cn.xylose.mitemod.breadskin;

import fi.dy.masa.malilib.gui.screen.ModsScreen;
import net.fabricmc.api.ModInitializer;
import cn.xylose.mitemod.breadskin.util.BreadSkinConfigs;

public class BreadSkin implements ModInitializer {

    @Override
    public void onInitialize() {
        BreadSkinConfigs.init();
        BreadSkinConfigs.getInstance().load();
        ModsScreen.getInstance().addConfig(BreadSkinConfigs.getInstance());
    }

}
