package cn.xylose.mitemod.breadskin.config;

public enum EnumNutritionInfoMode {
    Empty((nutrition, limit) -> {
        throw new IllegalCallerException();
    }),
    Exact((nutrition, limit) -> nutrition + "/" + limit),
    Percentage((nutrition, limit) -> (nutrition * 100 / limit) + "%"),
    Mixed((nutrition, limit) -> nutrition + "/" + limit + " (" + (nutrition * 100 / limit) + "%)"),
    ;

    public final NutritionInfoFormatter formatter;

    EnumNutritionInfoMode(NutritionInfoFormatter formatter) {
        this.formatter = formatter;
    }

    public interface NutritionInfoFormatter {
        String format(int nutrition, int limit);
    }
}
