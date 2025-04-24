package cn.xylose.mitemod.breadskin.render;

public record NutritionInfo(int protein, int phytonutrients, int essential_fats) {

    public static final NutritionInfo ZERO = new NutritionInfo(0, 0, 0);

    public boolean shouldDraw() {
        return protein > -1 && phytonutrients > -1;
    }
}
