package studio.fantasyit.tour_guide.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.common.NeoForge;
import studio.fantasyit.tour_guide.api.event.TourDataRegisterEvent;
import studio.fantasyit.tour_guide.data.ITourDataFactory;
import studio.fantasyit.tour_guide.data.TourData;
import studio.fantasyit.tour_guide.integration.Integrations;
import studio.fantasyit.tour_guide.integration.kubejs.KubeJSPort;

import java.util.HashMap;
import java.util.Map;

public class TourDataManager {
    private static final Map<ResourceLocation, ITourDataFactory> TOURS = new HashMap<>();

    public static void register(ResourceLocation id, ITourDataFactory tourData) {
        TOURS.put(id, tourData);
    }

    public static void clearAndBroadcastRegister() {
        TOURS.clear();
        NeoForge.EVENT_BUS.post(new TourDataRegisterEvent());
        if (Integrations.kjs())
            KubeJSPort.reloaded();
    }

    public static TourData get(ResourceLocation id, ServerPlayer player) {
        return TOURS.get(id).create(player, id);
    }
}