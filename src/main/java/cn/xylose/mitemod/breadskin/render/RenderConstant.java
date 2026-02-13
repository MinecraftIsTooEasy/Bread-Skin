package cn.xylose.mitemod.breadskin.render;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import moddedmite.rustedironcore.api.util.FabricUtil;

public class RenderConstant {
    public static int getLeftBarXOffset() {
        if (BreadSkinConfigs.LeftBarXOffset.isModified()) {
            return BreadSkinConfigs.LeftBarXOffset.getIntegerValue();
        }
        if (FabricUtil.isModLoaded("mite_offhand")) {
            return -30;
        }
        return BreadSkinConfigs.LeftBarXOffset.getIntegerValue();
    }

    public static int getRightBarXOffset() {
        return BreadSkinConfigs.RightBarXOffset.getIntegerValue();
    }
}
