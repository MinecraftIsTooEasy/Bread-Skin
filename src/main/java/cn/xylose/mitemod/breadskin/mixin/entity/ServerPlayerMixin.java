package cn.xylose.mitemod.breadskin.mixin.entity;

import cn.xylose.mitemod.breadskin.network.S2CUpdateNutrition;
import net.minecraft.NetServerHandler;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Shadow
    private int phytonutrients;
    @Shadow
    private int protein;
    @Shadow
    public NetServerHandler playerNetServerHandler;
    @Unique
    private int last_phytonutrients;
    @Unique
    private int last_protein;


    @Inject(method = "onUpdateEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/FoodStats;getHunger()F"))
    private void updateNutrition(CallbackInfo ci) {
        if (this.phytonutrients != this.last_phytonutrients || this.protein != this.last_protein) {
            this.playerNetServerHandler.sendPacketToPlayer(new S2CUpdateNutrition(phytonutrients, protein));
            this.last_phytonutrients = this.phytonutrients;
            this.last_protein = this.protein;
        }
    }
}
