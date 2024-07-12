package cn.xylose.mitemod.breadskin.network;

import cn.xylose.mitemod.breadskin.api.BreadSkinClientPlayer;
import net.minecraft.NetClientHandler;
import net.minecraft.NetHandler;
import net.minecraft.NetServerHandler;
import net.minecraft.Packet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2CUpdateNutrition extends Packet {
    private int protein;
    private int phytonutrients;

    public S2CUpdateNutrition() {
    }

    public S2CUpdateNutrition(int phytonutrients, int protein) {
        this.phytonutrients = phytonutrients;
        this.protein = protein;
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        this.protein = dataInput.readInt();
        this.phytonutrients = dataInput.readInt();
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.protein);
        dataOutput.writeInt(this.phytonutrients);
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        if (netHandler instanceof NetServerHandler) {
            throw new IllegalCallerException();
        }
        if (netHandler instanceof NetClientHandler netClientHandler) {
            BreadSkinClientPlayer clientPlayer = netClientHandler.mc.thePlayer;
            clientPlayer.breadSkin$SetPhytonutrients(this.phytonutrients);
            clientPlayer.breadSkin$SetProtein(this.protein);
        }
    }

    @Override
    public int getPacketSize() {
        return 8;
    }
}
