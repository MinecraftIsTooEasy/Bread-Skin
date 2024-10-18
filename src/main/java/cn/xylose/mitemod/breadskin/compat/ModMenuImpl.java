package cn.xylose.mitemod.breadskin.compat;

import cn.xylose.mitemod.breadskin.config.BreadSkinConfigs;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return s -> BreadSkinConfigs.getInstance().getConfigScreen(s);
    }
}
