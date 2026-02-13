package cn.xylose.mitemod.breadskin.feat;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import moddedmite.rustedironcore.api.player.ClientPlayerAPI;
import net.minecraft.ClientPlayer;

public class SyncedNutrition {
    public static NutritionInfo getNutritionInfo(ClientPlayer player) {
        return new NutritionInfo(
                ClientPlayerAPI.getProtein(player),
                ClientPlayerAPI.getPhytonutrients(player),
                ClientPlayerAPI.getEssentialFats(player)
        );
    }

    public static int getNutritionLimit(ClientPlayer player) {
        if (BreadSkinConfigs.NutritionLimitOverride.isModified()) {
            return BreadSkinConfigs.NutritionLimitOverride.getIntegerValue();
        }

        int nutritionLimit = ClientPlayerAPI.getNutritionLimit(player);
        if (nutritionLimit > 0) {
            return nutritionLimit;
        }
        // falls back
        return BreadSkinConfigs.NutritionLimitOverride.getIntegerValue();
    }
}
