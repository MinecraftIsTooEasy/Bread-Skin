package cn.xylose.mitemod.breadskin.compat;

import cn.xylose.mitemod.breadskin.render.NutritionInfo;
import moddedmite.rustedironcore.api.player.ClientPlayerAPI;
import net.minecraft.ClientPlayer;

// to avoid calling import at other places
public class NutritionServiceWrapper {
    public static NutritionInfo getNutritionInfo(ClientPlayer player) {
        return new NutritionInfo(ClientPlayerAPI.getProtein(player), ClientPlayerAPI.getPhytonutrients(player), ClientPlayerAPI.getEssentialFats(player));
    }

    public static int getNutritionLimit(ClientPlayer player) {
        return ClientPlayerAPI.getNutritionLimit(player);
    }
}
