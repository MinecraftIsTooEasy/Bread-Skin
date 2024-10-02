package cn.xylose.mitemod.breadskin.mixin.gui;

import cn.xylose.mitemod.breadskin.api.BreadSkinClientPlayer;
import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import cn.xylose.mitemod.breadskin.render.RenderHud;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin({GuiIngame.class})
public abstract class GuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;
    @Shadow
    private int updateCounter;
    @Shadow
    @Final
    private Random rand;

    @Inject(
            method = "func_110327_a",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/EntityClientPlayerMP;getHealth()F"
            )
    )
    private void injectFullnessBreadSkin(int par1, int par2, CallbackInfo ci) {
        RenderHud.drawBread(this.mc.ingameGUI, this.mc, this.rand, par1, par2);
    }

    @Inject(method = "func_110327_a", at = @At("HEAD"))
    private void injectAirBreadSkin(int par1, int par2, CallbackInfo ci) {
        this.rand.setSeed(this.updateCounter * 312871L);
        RenderHud.drawAir(this.mc.ingameGUI, this.mc, this.rand, par1, par2);
    }

    @Inject(method = "func_110327_a",
            at = {@At(value = "INVOKE", target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V", ordinal = 1),
                    @At(value = "INVOKE", target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V", ordinal = 3)},
            cancellable = true)
    private void doNotDrawHungerAndAir(int par1, int par2, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(
            locals = LocalCapture.CAPTURE_FAILHARD,
            method = "func_110327_a(II)V",
            at = @At(
                    value = "INVOKE_STRING",
                    target = "Lnet/minecraft/Profiler;startSection(Ljava/lang/String;)V",
                    args = "ldc=armor",
                    shift = At.Shift.BEFORE
            )
    )
    private void nutritionBar(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodStats var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
        if (BreadSkinConfigs.DrawNutritionBar.getBooleanValue()) {
            BreadSkinClientPlayer thePlayer = (BreadSkinClientPlayer) this.mc.thePlayer;
            int protein = thePlayer.breadSkin$GetProtein();
            int phytonutrients = thePlayer.breadSkin$GetPhytonutrients();
            int essential_fats = thePlayer.breadSkin$GetEssentialFats();
            if (protein == 0 || phytonutrients == 0) {
                return;
            }
            switch (BreadSkinConfigs.NutritionBarMode.getEnumValue()) {
                case Mixed -> RenderHud.drawNutrientsBarMixed(this, this.mc, var12, var13, protein, phytonutrients, essential_fats);
                case Separate ->
                        RenderHud.drawNutrientsBarSeparate(this, this.mc, var12, var13, protein, phytonutrients, essential_fats);
            }
        }
    }
}