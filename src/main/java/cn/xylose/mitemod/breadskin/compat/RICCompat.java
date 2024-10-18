package cn.xylose.mitemod.breadskin.compat;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import cn.xylose.mitemod.breadskin.render.NutritionInfo;
import fi.dy.masa.malilib.ManyLib;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.impl.util.version.SemanticVersionImpl;
import net.minecraft.ClientPlayer;
import net.xiaoyu233.fml.FishModLoader;

public class RICCompat {

    public static boolean ricLoaded = false;

    static {
        FishModLoader.getModContainer("rusted_iron_core").ifPresent(x -> {
            Version version = x.getMetadata().getVersion();
            int compare = -1;
            try {
                Version compareTo = new SemanticVersionImpl("1.3.3", false);
                compare = version.compareTo(compareTo);
            } catch (VersionParsingException e) {
                e.printStackTrace();
            }
            if (compare >= 0) {
                ricLoaded = true;
            }
        });
    }

    static boolean loggedInfo = false;

    public static NutritionInfo getNutritionInfo(ClientPlayer player) {
        if (!ricLoaded) {
            throw new IllegalStateException("should be called when ric is loaded");
        }
        try {
            return NutritionServiceWrapper.getNutritionInfo(player);
        } catch (Exception e) {
            if (!loggedInfo) {
                ManyLib.logger.warn("failed to get nutrition info");
                e.printStackTrace();
                loggedInfo = true;
            }
        }
        return NutritionInfo.ZERO;
    }

    static boolean loggedLimit = false;

    public static int getLimit(ClientPlayer player) {
        if (!ricLoaded) {
            throw new IllegalStateException("should be called when ric is loaded");
        }
        if (!BreadSkinConfigs.NutritionLimitOverride.isModified()) {
            try {
                int nutritionLimit = NutritionServiceWrapper.getNutritionLimit(player);
                if (nutritionLimit > 0) {
                    return nutritionLimit;
                }
            } catch (Exception e) {
                if (!loggedLimit) {
                    ManyLib.logger.warn("failed to get nutrition limit");
                    e.printStackTrace();
                    loggedLimit = true;
                }
            }
        }
        return BreadSkinConfigs.NutritionLimitOverride.getIntegerValue();
    }

}
