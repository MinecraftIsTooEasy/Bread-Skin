package cn.xylose.mitemod.breadskin.mixin.network;

import cn.xylose.mitemod.breadskin.api.BreadSkinClientPlayer;
import cn.xylose.mitemod.breadskin.api.BreadSkinNetHandler;
import cn.xylose.mitemod.breadskin.network.S2CUpdateNutrition;
import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin implements BreadSkinNetHandler {
    @Shadow
    private Minecraft mc;

    @Override
    public void breadSkin$HandleUpdateNutrition(S2CUpdateNutrition packet) {
        BreadSkinClientPlayer clientPlayer = this.mc.thePlayer;
        clientPlayer.breadSkin$SetPhytonutrients(packet.getPhytonutrients());
        clientPlayer.breadSkin$SetProtein(packet.getProtein());
    }
}
