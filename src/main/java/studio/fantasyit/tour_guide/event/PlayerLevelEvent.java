package studio.fantasyit.tour_guide.event;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourManager;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerLevelEvent {
    @SubscribeEvent
    public static void onPlayerLevel(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp)
            TourManager.remove(sp);
    }
}
