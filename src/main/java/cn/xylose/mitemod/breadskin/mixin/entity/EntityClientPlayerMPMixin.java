package cn.xylose.mitemod.breadskin.mixin.entity;

import cn.xylose.mitemod.breadskin.api.BreadSkinClientPlayer;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityClientPlayerMP.class)
public abstract class EntityClientPlayerMPMixin extends ClientPlayer implements BreadSkinClientPlayer {

    @Unique
    private int phytonutrients;
    @Unique
    private int protein;
    @Unique
    private int essential_fats;

    public EntityClientPlayerMPMixin(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler) {
        super(par1Minecraft, par2World, par3Session, 0);
    }

    @Override
    public int breadSkin$GetPhytonutrients() {
        return this.phytonutrients;
    }

    @Override
    public void breadSkin$SetPhytonutrients(int phytonutrients) {
        this.phytonutrients = phytonutrients;
    }

    @Override
    public int breadSkin$GetProtein() {
        return this.protein;
    }

    @Override
    public void breadSkin$SetProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int breadSkin$GetEssentialFats() {
        return this.essential_fats;
    }

    @Override
    public void breadSkin$SetEssentialFats(int essential_fats) {
        this.essential_fats = essential_fats;
    }

}
