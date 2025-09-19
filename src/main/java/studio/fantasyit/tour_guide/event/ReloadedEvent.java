package studio.fantasyit.tour_guide.event;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourDataManager;
import studio.fantasyit.tour_guide.client.ClientNetworkHooks;
import studio.fantasyit.tour_guide.data.ItemTourGuide;
import studio.fantasyit.tour_guide.network.C2SRequestTriggerableItems;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ReloadedEvent {

    @SubscribeEvent
    public static void onReloaded(TagsUpdatedEvent event) {
        TourDataManager.clearAndBroadcastRegister();
        if (event.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
            ItemTourGuide.clearAndBroadcastRegister();
        } else {
            ClientNetworkHooks.requestTriggerableItems();
        }
    }
}
