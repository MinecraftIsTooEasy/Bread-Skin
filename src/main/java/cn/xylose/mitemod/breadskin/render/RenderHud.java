package cn.xylose.mitemod.breadskin.render;

import cn.xylose.mitemod.breadskin.BreadSkin;
import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import cn.xylose.mitemod.breadskin.config.EnumNutritionInfoMode;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderHud {
    private static final ResourceLocation icons_breadSkin = new ResourceLocation("textures/gui/icons_breadskin.png");

    public static void drawBread(GuiIngame guiIngame, Minecraft mc, Random rand, int par1, int par2) {
        AttributeInstance var10 = mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 10 - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue() * 10;
        int displayYAppleSkin = var13 - (var16 - 1) * var17;
        FoodStats foodStats = mc.thePlayer.getFoodStats();
        mc.getTextureManager().bindTexture(icons_breadSkin);
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
                            displayY = var13 - 10 + (rand.nextInt(3) - 1) - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue() * 10;
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
        int displayY;
        if (!BreadSkinConfigs.Apple_Skin_Mode.getBooleanValue())
            displayY = var13 - (var16 - 1) * var17 - 20 - BreadSkinConfigs.Saturation_Hud_Y.getIntegerValue();
        else
            displayY = var13 - (var16 - 1) * var17 - 10;
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

    public static void drawNutrientsBarSeparate(Gui gui, Minecraft mc, int var12, int var13, int protein, int phytonutrients, int essential_fats) {
        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int scaledWidth = sr.getScaledWidth();
        FontRenderer fontRenderer = mc.fontRenderer;
        int yLevel = var13 + 32 + BreadSkinConfigs.BarYOffset.getIntegerValue();
        int xStartRight = scaledWidth / 2 + 100 + BreadSkinConfigs.BarXOffset.getIntegerValue();
        int xStartLeft = scaledWidth / 2 - 100 - 109 - BreadSkinConfigs.BarXOffset.getIntegerValue();
        EnumNutritionInfoMode infoMode = BreadSkinConfigs.NutritionInfoMode.getEnumValue();
        if (infoMode != EnumNutritionInfoMode.Empty) {
            int limit = BreadSkinConfigs.NutritionLimit.getIntegerValue();
            String phytonutrientsString = infoMode.formatter.format(phytonutrients, limit);
            gui.drawString(fontRenderer, phytonutrientsString, xStartRight, yLevel - 8, 16777215);
            String proteinString = infoMode.formatter.format(protein, limit);
            gui.drawString(fontRenderer, proteinString, xStartLeft + 109 - fontRenderer.getStringWidth(proteinString), yLevel - 8, 16777215);
            String essentialFatsString = infoMode.formatter.format(essential_fats, limit);
            if (BreadSkinConfigs.DrawEssentialFatsNutritionBar.getBooleanValue())
                gui.drawString(fontRenderer, essentialFatsString, xStartRight, yLevel - 24, 16777215);
        }
        drawPhytonutrients(gui, mc, xStartRight, yLevel, phytonutrients, true);
        drawProtein(gui, mc, xStartLeft, yLevel, protein, true);
        drawEssentialFats(gui, mc, xStartRight, yLevel - 16, essential_fats, true);
    }
    public static void drawNutrientsBarMixed(Gui gui, Minecraft mc, int var12, int var13, int protein, int phytonutrients, int essential_fats) {
        ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int scaledWidth = sr.getScaledWidth();
        int var25 = var13 + 32 + BreadSkinConfigs.BarYOffset.getIntegerValue();
        int var26 = scaledWidth / 2 - 209 + BreadSkinConfigs.BarXOffset.getIntegerValue();
        if (protein > phytonutrients) {
            drawProtein(gui, mc, var26, var25, protein, true);
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, false);
        } else if (protein < phytonutrients && protein > essential_fats) {
            drawEssentialFats(gui, mc, var26, var25, essential_fats, false);
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, true);
            drawProtein(gui, mc, var26, var25, protein, false);
        } else if (essential_fats > phytonutrients && essential_fats > protein) {
            drawProtein(gui, mc, var26, var25, protein, true);
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, false);
            drawEssentialFats(gui, mc, var26, var25, essential_fats, false);
        } else {
            drawEssentialFats(gui, mc, var26, var25, essential_fats, false);
            drawProtein(gui, mc, var26, var25, protein, true);
            drawPhytonutrients(gui, mc, var26, var25, phytonutrients, false);
        }
    }

    private static void drawPhytonutrients(Gui gui, Minecraft mc, int x, int y, int phytonutrients, boolean drawBackgound) {
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 1.0F, 1.0F);
        GL11.glTranslatef(x / 1.5F, 0, 0);
        mc.getTextureManager().bindTexture(icons_breadSkin);
        if (drawBackgound) {
            gui.drawTexturedModalRect(x, y, 0, 106, 182, 6);
        }
        gui.drawTexturedModalRect(x, y, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
        GL11.glPopMatrix();
    }

    private static void drawProtein(Gui gui, Minecraft mc, int x, int y, int protein, boolean drawBackground) {
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 1.0F, 1.0F);
        GL11.glTranslatef(x / 1.5F, 0, 0);
        mc.getTextureManager().bindTexture(icons_breadSkin);
        if (drawBackground) {
            gui.drawTexturedModalRect(x, y, 0, 106, 182, 6);
        }
        gui.drawTexturedModalRect(x, y, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
        GL11.glPopMatrix();
    }

    private static void drawEssentialFats(Gui gui, Minecraft mc, int x, int y, int essential_fats, boolean drawBackground) {
        if (BreadSkinConfigs.DrawEssentialFatsNutritionBar.getBooleanValue()) {
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            GL11.glTranslatef(x / 1.5F, 0, 0);
            mc.getTextureManager().bindTexture(icons_breadSkin);
            if (drawBackground) {
                gui.drawTexturedModalRect(x, y, 0, 106, 182, 6);
            }
            gui.drawTexturedModalRect(x, y, 0, 112, (int) (182.0F * getRateNutrient(essential_fats)), 6);
            GL11.glPopMatrix();
        } else {
            gui.drawString(mc.fontRenderer, "", 0, 0, 16777215);
        }
    }

    private static float getRateNutrient(long par1) {
        if (BreadSkinConfigs.SecondaryDecrement.getBooleanValue()) {
            par1 *= par1;
            par1 /= BreadSkinConfigs.NutritionLimit.getIntegerValue();
        }
        return (float) par1 / BreadSkinConfigs.NutritionLimit.getIntegerValue();
    }
}
