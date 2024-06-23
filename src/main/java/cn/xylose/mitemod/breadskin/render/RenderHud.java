package cn.xylose.mitemod.breadskin.render;

import cn.xylose.mitemod.breadskin.api.BreadSkinClientPlayer;
import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderHud {
    private static final ResourceLocation icons_breadskin = new ResourceLocation("textures/gui/icons_breadskin.png");

    public static void drawBread(GuiIngame guiIngame, Minecraft mc, Random rand, int par1, int par2) {
        AttributeInstance var10 = mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 10 - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue();
        int displayYAppleSkin = var13 - (var16 - 1) * var17;
        FoodStats foodStats = mc.thePlayer.getFoodStats();
        mc.getTextureManager().bindTexture(icons_breadskin);
        Entity ridingEntity = mc.thePlayer.ridingEntity;
        if (BreadSkinConfigs.Apple_Skin_Mode.getBooleanValue()) {
            if (!(ridingEntity != null && !(ridingEntity instanceof EntityBoat))) {
                mc.mcProfiler.endStartSection("fullness");
                int fullness = foodStats.getSatiation();
                for (int temp = 0; temp < 10; temp++) {
                    int textureStartPoint = 79;
                    if (BreadSkinConfigs.Display_Saturation.getBooleanValue()) {
                        if (mc.thePlayer.isPotionActive(Potion.hunger)) {
                            textureStartPoint += 18;
                        }
                        int displayX = var12 - temp * 8 - 9;
                        if (temp * 2 + 1 < fullness) {
                            guiIngame.drawTexturedModalRect(displayX, displayYAppleSkin, textureStartPoint + 9, 18, 9, 9);
                        }
                        if (temp * 2 + 1 == fullness) {
                            guiIngame.drawTexturedModalRect(displayX, displayYAppleSkin, textureStartPoint + 18, 18, 9, 9);
                        }
                    }
                }
            }
        } else {
            if (!(ridingEntity != null && !(ridingEntity instanceof EntityBoat))) {
                mc.mcProfiler.endStartSection("fullness");
                int fullness = foodStats.getSatiation();
                for (int temp = 0; temp < 10; temp++) {
                    int textureStartPoint = 34;
                    int textureBase = 0;
                    if (BreadSkinConfigs.Display_Saturation.getBooleanValue()) {
                        if (mc.thePlayer.isPotionActive(Potion.hunger)) {
                            textureStartPoint += 27;
                            textureBase = 9;
                        }
                        if (mc.thePlayer.isHungry() && guiIngame.getUpdateCounter() % (fullness * 3 + 1) == 0) {
                            displayY = var13 - 10 + (rand.nextInt(3) - 1) - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue();
                        }
                        int displayX = var12 - temp * 8 - 9;
                        if (temp < mc.thePlayer.getFoodStats().getSatiationLimit() / 2) {
                            guiIngame.drawTexturedModalRect(displayX, displayY, 34 + textureBase * 3, 18, 9, 9);
                        }
                        if (temp * 2 + 1 < fullness) {
                            guiIngame.drawTexturedModalRect(displayX, displayY, textureStartPoint + 9, 18, 9, 9);
                        }
                        if (temp * 2 + 1 == fullness) {
                            guiIngame.drawTexturedModalRect(displayX, displayY, textureStartPoint + 18, 18, 9, 9);
                        }
                    }
                }
            }
        }
    }

    public static void drawAir(GuiIngame guiIngame, Minecraft mc, Random rand, int par1, int par2) {
        AttributeInstance var10 = mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = mc.thePlayer.getAbsorptionAmount();
        FoodStats foodStats = mc.thePlayer.getFoodStats();
        int food = foodStats.getNutrition();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 20 - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue();
        int var23;
        int var25;
        int var26;
        int var27;
        int var28;
        mc.mcProfiler.endStartSection("air");
        if (mc.thePlayer.isInsideOfMaterial(Material.water)) {
            var23 = mc.thePlayer.getAir();
            var28 = MathHelper.ceiling_double_int((double) (var23 - 2) * 10.0 / 300.0);
            var25 = (byte) (MathHelper.ceiling_double_int((double) var23 * 10.0 / 300.0) - var28);

            for (var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    guiIngame.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 16, 18, 9, 9);
                } else {
                    guiIngame.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 25, 18, 9, 9);
                }
            }
        }
        Entity var34 = mc.thePlayer.ridingEntity;
        if (!(var34 != null && !(var34 instanceof EntityBoat))) {
            mc.mcProfiler.endStartSection("food");
            for (var23 = 0; var23 < 10; ++var23) {
                var28 = var13;
                var25 = 16;
                byte var36 = 0;
                if (mc.thePlayer.isPotionActive(Potion.hunger)) {
                    var25 += 36;
                    var36 = 13;
                }
                if (mc.thePlayer.isHungry() && guiIngame.getUpdateCounter() % (food * 3 + 1) == 0) {
                    var28 = var13 + (rand.nextInt(3) - 1);
                }
                var27 = var12 - var23 * 8 - 9;
                if (var23 < mc.thePlayer.getFoodStats().getNutritionLimit() / 2) {
                    guiIngame.drawTexturedModalRect(var27, var28, 16 + var36 * 9, 27, 9, 9);
                }
                if (var23 * 2 + 1 < food) {
                    guiIngame.drawTexturedModalRect(var27, var28, var25 + 36, 27, 9, 9);
                }
                if (var23 * 2 + 1 == food) {
                    guiIngame.drawTexturedModalRect(var27, var28, var25 + 45, 27, 9, 9);
                }
            }
        }
    }

    public static void drawNutrientsBarSeparate(Gui gui, Minecraft mc, int var12, int var13) {
        BreadSkinClientPlayer thePlayer = (BreadSkinClientPlayer) mc.thePlayer;
        int protein = thePlayer.breadSkin$GetProtein();
        int phytonutrients = thePlayer.breadSkin$GetPhytonutrients();
        int var25 = var13 + 32;
        int var26 = var12 + 240 + BreadSkinConfigs.RightBarOffset.getIntegerValue();
        if (BreadSkinConfigs.ExactNutrition.getBooleanValue()) {
            gui.drawString(mc.fontRenderer, phytonutrients + "/" + 160000, var26 - 167, var25 - 8, 16777215);
        }
        drawPhytonutrients(gui, mc, var26, var25, phytonutrients, true);
        var26 = var12 - 303 + BreadSkinConfigs.LeftBarOffset.getIntegerValue();
        if (BreadSkinConfigs.ExactNutrition.getBooleanValue()) {
            gui.drawString(mc.fontRenderer, protein + "/" + 160000, var26, var25 - 8, 16777215);
        }
        drawProtein(gui, mc, var26, var25, protein, true);
    }

    public static void drawNutrientsBarMixed(Gui gui, Minecraft mc, int var12, int var13) {
        BreadSkinClientPlayer thePlayer = (BreadSkinClientPlayer) mc.thePlayer;
        int protein = thePlayer.breadSkin$GetProtein();
        int phytonutrients = thePlayer.breadSkin$GetPhytonutrients();
        int var25 = var13 + 32;
        int var26 = var12 - 303 + BreadSkinConfigs.LeftBarOffset.getIntegerValue();
        if (protein > phytonutrients) {
            drawProtein(gui, mc, var26, var25, protein, true);
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, false);
        } else {
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, true);
            drawProtein(gui, mc, var26, var25, protein, false);
        }
    }

    private static void drawPhytonutrients(Gui gui, Minecraft mc, int x, int y, int phytonutrients, boolean drawBackgound) {
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(icons_breadskin);
        if (drawBackgound) {
            gui.drawTexturedModalRect(x, y, 0, 106, 182, 6);
        }
        gui.drawTexturedModalRect(x, y, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
        GL11.glPopMatrix();
    }

    private static void drawProtein(Gui gui, Minecraft mc, int x, int y, int protein, boolean drawBackgound) {
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(icons_breadskin);
        if (drawBackgound) {
            gui.drawTexturedModalRect(x, y, 0, 106, 182, 6);
        }
        gui.drawTexturedModalRect(x, y, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
        GL11.glPopMatrix();
    }

    private static float getRateNutrient(long par1) {
        if (BreadSkinConfigs.SecondaryDecrement.getBooleanValue()) {
            par1 *= par1;
            par1 /= 160000L;
            return (float) par1 / 160000.0F;
        } else {
            return (float) par1 / 160000.0F;
        }
    }
}
