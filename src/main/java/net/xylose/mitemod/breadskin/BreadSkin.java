package net.xylose.mitemod.breadskin;

import net.fabricmc.api.ModInitializer;
import net.xiaoyu233.fml.config.ConfigRegistry;
import net.xylose.mitemod.breadskin.util.Configs;

import java.io.File;
import java.util.Optional;

public class BreadSkin implements ModInitializer {

    private static final ConfigRegistry CONFIG_REGISTRY = new ConfigRegistry(Configs.ROOT, new File("bread-skin.json"));

    @Override
    public void onInitialize() {
    }

    @Override
    public Optional<ConfigRegistry> createConfig() {
        return Optional.of(CONFIG_REGISTRY);
    }
}
