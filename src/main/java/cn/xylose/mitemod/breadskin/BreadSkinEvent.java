package cn.xylose.mitemod.breadskin;

import cn.xylose.mitemod.breadskin.network.S2CUpdateNutrition;
import com.google.common.eventbus.Subscribe;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;

public class BreadSkinEvent {
    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event) {
        event.register(true, false, S2CUpdateNutrition.class);
    }
}
