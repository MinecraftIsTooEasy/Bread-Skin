package net.xylose.mitemod.breadskin.mixin.gui;
 
import java.util.Random;
import net.minecraft.*;

import net.xylose.mitemod.breadskin.util.Configs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiIngame.class})
public abstract class GuiIngameMixin extends Gui {

    private static final ResourceLocation icons_breadskin = new ResourceLocation("textures/gui/icons_breadskin.png");


    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        super.drawTexturedModalRect(x, y, textureX, textureY, width, height);
    }

    @Shadow
    @Final
    private Minecraft mc;

    @Final
    private final Random rand = new Random();

    @Shadow
    private int updateCounter;

    @Inject(method = "func_110327_a",at = @At("HEAD"))
    private void injectFullness(int par1, int par2, CallbackInfo ci) {
        AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float)var10.getAttributeValue();
        float var15 = this.mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 10 - Configs.Hud_Y.get();
        FoodStats foodStats = this.mc.thePlayer.getFoodStats();
        this.mc.getTextureManager().bindTexture(icons_breadskin);
        this.mc.mcProfiler.endStartSection("fullness");
        int fullness = foodStats.getSatiation();
        for (int temp = 0; temp < 10; temp++) {
            int textureStartPoint = 34;
            int textureBase = 0;
            if (Configs.Display_Saturation.get()) {
                int satiation = foodStats.getSatiation();
                if (this.mc.thePlayer.isPotionActive(Potion.hunger)) {
                    textureStartPoint += 27;
                    textureBase = 9;
                }
                if (this.mc.thePlayer.isHungry() && this.updateCounter % (satiation * 3 + 1) == 0) {
                    displayY = var13 - 10 + (this.rand.nextInt(3) - 1) - Configs.Hud_Y.get();
                }
                int displayX = var12 - temp * 8 - 9;
                if (temp < this.mc.thePlayer.getFoodStats().getSatiationLimit() / 2) {
                    drawTexturedModalRect(displayX, displayY, 34 + textureBase * 3, 18, 9, 9);
                }
                if (temp * 2 + 1 < fullness) {
                    drawTexturedModalRect(displayX, displayY, textureStartPoint + 9, 18, 9, 9);
                }
                if (temp * 2 + 1 == fullness) {
                    drawTexturedModalRect(displayX, displayY, textureStartPoint + 18, 18, 9, 9);
                }
            }
        }
    }
    @Inject(method = "func_110327_a",at = @At("HEAD"))
    private void injectAir(int par1, int par2, CallbackInfo ci) {
        AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float)var10.getAttributeValue();
        float var15 = this.mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 20 - Configs.Hud_Y.get();
        int var23;
        int var25;
        int var26;
        int var28;
        this.mc.mcProfiler.endStartSection("air");
        if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
            var23 = this.mc.thePlayer.getAir();
            var28 = MathHelper.ceiling_double_int((double)(var23 - 2) * 10.0 / 300.0);
            var25 = (byte) (MathHelper.ceiling_double_int((double)var23 * 10.0 / 300.0) - var28);

            for(var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 16, 18, 9, 9);
                } else {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 25, 18, 9, 9);
                }
            }
        }
    }
    @Inject(method = "func_110327_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isInsideOfMaterial(Lnet/minecraft/Material;)Z"), cancellable = true)
    private void cancel(int par1, int par2, CallbackInfo ci) {
        this.mc.mcProfiler.endStartSection("air");
        ci.cancel();
    }

 }