package cn.xylose.mitemod.breadskin.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityStatsDump.class)
public class EntityStatsDumpMixin {
    @Shadow private static String newline = new String(System.getProperty("line.separator").getBytes());

    @Inject(
            method = "getStatsDump",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/ServerPlayer;getProtein()I",
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void addEssentialFatsValueToStatsDump(String header, EntityLivingBase entity_living_base, CallbackInfoReturnable<StringBuilder> cir, @Local StringBuilder sb) {
        EntityPlayer player = entity_living_base.isEntityPlayer() ? entity_living_base.getAsPlayer() : null;
        int essential_fats;
        int insulin_response;
        if (player != null) {
            essential_fats = player.getAsEntityPlayerMP().getEssentialFats();
            insulin_response = player.getInsulinResistance();
        } else {
            essential_fats = 0;
            insulin_response = 0;
        }
        sb.append("Insulin Response: ").append(insulin_response).append(" (Mild: ").append(100 * insulin_response / 48000).append("%, Moderate: ").append(100 * insulin_response / 96000).append("%, Severe: ").append(100 * insulin_response / 144000).append("%, Max: ").append(100 * insulin_response / 192000).append("%)").append(newline);
        sb.append("Essential Fats: ").append(essential_fats).append(" (").append(100 * essential_fats / 160000).append("%)").append(newline);
    }

}
