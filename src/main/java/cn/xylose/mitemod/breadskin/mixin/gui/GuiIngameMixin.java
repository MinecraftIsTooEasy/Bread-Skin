package cn.xylose.mitemod.breadskin.mixin.gui;
 
import java.util.Random;

import cn.xylose.mitemod.breadskin.util.BreadSkinConfigs;
import net.minecraft.*;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiIngame.class})
public abstract class GuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;
    @Final
    private final Random rand = new Random();
    @Shadow
    private int updateCounter;
    private static final ResourceLocation icons_breadskin = new ResourceLocation("textures/gui/icons_breadskin.png");

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        super.drawTexturedModalRect(x, y, textureX, textureY, width, height);
    }

    @Inject(method = "func_110327_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;getHealth()F"))
    private void injectFullnessBreadSkin(int par1, int par2, CallbackInfo ci) {
        AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = this.mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 10 - BreadSkinConfigs.Saturation_Hud_Y.get();
        int displayYAppleSkin = var13 - (var16 - 1) * var17;
        FoodStats foodStats = this.mc.thePlayer.getFoodStats();
        this.mc.getTextureManager().bindTexture(icons_breadskin);
        Entity ridingEntity = this.mc.thePlayer.ridingEntity;
        if (!(BreadSkinConfigs.Apple_Skin_Mode.get())) {
            if (!(ridingEntity != null && !(ridingEntity instanceof EntityBoat))) {
                this.mc.mcProfiler.endStartSection("fullness");
                int fullness = foodStats.getSatiation();
                for (int temp = 0; temp < 10; temp++) {
                    int textureStartPoint = 34;
                    int textureBase = 0;
                    if (BreadSkinConfigs.Display_Saturation.get()) {
                        if (this.mc.thePlayer.isPotionActive(Potion.hunger)) {
                            textureStartPoint += 27;
                            textureBase = 9;
                        }
                        if (this.mc.thePlayer.isHungry() && this.updateCounter % (fullness * 3 + 1) == 0) {
                            displayY = var13 - 10 + (this.rand.nextInt(3) - 1) - BreadSkinConfigs.Saturation_Hud_Y.get();
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
        } else {
            if (!(ridingEntity != null && !(ridingEntity instanceof EntityBoat))) {
                this.mc.mcProfiler.endStartSection("fullness");
                int fullness = foodStats.getSatiation();
                for (int temp = 0; temp < 10; temp++) {
                    int textureStartPoint = 79;
                    if (BreadSkinConfigs.Display_Saturation.get()) {
                        if (this.mc.thePlayer.isPotionActive(Potion.hunger)) {
                            textureStartPoint += 18;
                        }
                        int displayX = var12 - temp * 8 - 9;
                        if (temp * 2 + 1 < fullness) {
                            drawTexturedModalRect(displayX, displayYAppleSkin, textureStartPoint + 9, 18, 9, 9);
                        }
                        if (temp * 2 + 1 == fullness) {
                            drawTexturedModalRect(displayX, displayYAppleSkin, textureStartPoint + 18, 18, 9, 9);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "func_110327_a", at = @At("HEAD"))
    private void injectAirBreadSkin(int par1, int par2, CallbackInfo ci) {
        this.rand.setSeed((long)(this.updateCounter * 312871));
        AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = this.mc.thePlayer.getAbsorptionAmount();
        FoodStats foodStats = this.mc.thePlayer.getFoodStats();
        int food = foodStats.getNutrition();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 20 - BreadSkinConfigs.Saturation_Hud_Y.get();
        int var23;
        int var25;
        int var26;
        int var27;
        int var28;
        this.mc.mcProfiler.endStartSection("air");
        if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
            var23 = this.mc.thePlayer.getAir();
            var28 = MathHelper.ceiling_double_int((double) (var23 - 2) * 10.0 / 300.0);
            var25 = (byte) (MathHelper.ceiling_double_int((double) var23 * 10.0 / 300.0) - var28);

            for (var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 16, 18, 9, 9);
                } else {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 25, 18, 9, 9);
                }
            }
        }
        Entity var34 = this.mc.thePlayer.ridingEntity;
        if (!(var34 != null && !(var34 instanceof EntityBoat))) {
            this.mc.mcProfiler.endStartSection("food");
            for (var23 = 0; var23 < 10; ++var23) {
                var28 = var13;
                var25 = 16;
                byte var36 = 0;
                if (this.mc.thePlayer.isPotionActive(Potion.hunger)) {
                    var25 += 36;
                    var36 = 13;
                }
                if (this.mc.thePlayer.isHungry() && this.updateCounter % (food * 3 + 1) == 0) {
                    var28 = var13 + (this.rand.nextInt(3) - 1);
                }
                var27 = var12 - var23 * 8 - 9;
                if (var23 < this.mc.thePlayer.getFoodStats().getNutritionLimit() / 2) {
                    this.drawTexturedModalRect(var27, var28, 16 + var36 * 9, 27, 9, 9);
                }
                if (var23 * 2 + 1 < food) {
                    this.drawTexturedModalRect(var27, var28, var25 + 36, 27, 9, 9);
                }
                if (var23 * 2 + 1 == food) {
                    this.drawTexturedModalRect(var27, var28, var25 + 45, 27, 9, 9);
                }
            }
        }
    }

    @Inject(method = "func_110327_a",
            at = {@At(value = "INVOKE", target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V", ordinal = 1),
                    @At(value = "INVOKE", target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V", ordinal = 3)},
            cancellable = true)
    private void doNotDrawHungerAndAir(int par1, int par2, CallbackInfo ci) {
        ci.cancel();
    }
 }