package net.xylose.mitemod.breadskin;

import fi.dy.masa.malilib.gui.screen.ModsScreen;
import net.fabricmc.api.ModInitializer;
import net.xylose.mitemod.breadskin.util.BreadSkinConfigs;

public class BreadSkin implements ModInitializer {
//    public static final int CONFIG_VERSION = 1;
//    private final ConfigRegistry configRegistry = new ConfigRegistry(BreadSkinConfigs.ROOT, BreadSkinConfigs.CONFIG_FILE);
    @Override
    public void onInitialize() {
        BreadSkinConfigs.init();
        BreadSkinConfigs.getInstance().load();
        ModsScreen.getInstance().addConfig(BreadSkinConfigs.getInstance());
    }

//    @Override
//    public Optional<ConfigRegistry> createConfig() {
//        return Optional.of(this.configRegistry);
//    }
}
