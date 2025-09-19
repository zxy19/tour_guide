package studio.fantasyit.tour_guide.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.ClientNetworkHooks;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class PlayerClientLevelEvent {
    @SubscribeEvent
    public static void onPlayerLevel(PlayerEvent.PlayerLoggedInEvent event) {
        ClientNetworkHooks.requestTriggerableItems();
    }
}
