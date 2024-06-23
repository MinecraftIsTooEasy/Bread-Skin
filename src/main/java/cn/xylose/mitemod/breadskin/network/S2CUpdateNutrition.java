package cn.xylose.mitemod.breadskin.network;

import cn.xylose.mitemod.breadskin.api.BreadSkinNetHandler;
import net.minecraft.NetHandler;
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

    public int getProtein() {
        return protein;
    }

    public int getPhytonutrients() {
        return phytonutrients;
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
        ((BreadSkinNetHandler) netHandler).breadSkin$HandleUpdateNutrition(this);
    }

    @Override
    public int getPacketSize() {
        return 8;
    }
}
