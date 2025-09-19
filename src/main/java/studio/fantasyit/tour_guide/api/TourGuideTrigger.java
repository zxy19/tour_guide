package studio.fantasyit.tour_guide.api;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import studio.fantasyit.tour_guide.network.C2SClientTrigger;
import studio.fantasyit.tour_guide.network.Network;

import java.util.Optional;

public class TourGuideTrigger {
    public static void trigger(ServerPlayer player, String key) {
        trigger(player, key, null);
    }

    public static void trigger(ServerPlayer player, String key, Object data) {
        Optional.ofNullable(TourManager.get(player)).ifPresent(t -> t.receiveTrigger(key, data));
    }

    public static void trigger(String key) {
        trigger(key, null);
    }

    public static void trigger(String key, Object data) {
        TourManager.each(
                t -> t.receiveTrigger(key, data)
        );
    }

    public static void triggerClient(String key) {
        PacketDistributor.sendToServer(new C2SClientTrigger(key));
    }
}
