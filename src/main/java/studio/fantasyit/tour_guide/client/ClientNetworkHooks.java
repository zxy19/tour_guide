package studio.fantasyit.tour_guide.client;

import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.PacketDistributor;
import studio.fantasyit.tour_guide.network.C2SRequestTriggerableItems;

public class ClientNetworkHooks {
    public static void requestTriggerableItems() {
        if (Minecraft.getInstance().player == null)
            return;
        PacketDistributor.sendToServer(new C2SRequestTriggerableItems());
    }
}
