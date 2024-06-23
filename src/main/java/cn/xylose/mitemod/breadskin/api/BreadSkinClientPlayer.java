package cn.xylose.mitemod.breadskin.api;

public interface BreadSkinClientPlayer {
    default int breadSkin$GetPhytonutrients() {
        throw new IllegalStateException("Should be implemented in Mixin");
    }

    default void breadSkin$SetPhytonutrients(int phytonutrients) {
        throw new IllegalStateException("Should be implemented in Mixin");
    }

    default int breadSkin$GetProtein() {
        throw new IllegalStateException("Should be implemented in Mixin");
    }

    default void breadSkin$SetProtein(int protein) {
        throw new IllegalStateException("Should be implemented in Mixin");
    }
}
