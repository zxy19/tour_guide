package studio.fantasyit.tour_guide.event;

import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourDataManager;
import studio.fantasyit.tour_guide.data.ItemTourGuide;
import studio.fantasyit.tour_guide.network.C2SRequestTriggerableItems;
import studio.fantasyit.tour_guide.network.Network;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReloadedEvent {

    @SubscribeEvent
    public static void onReloaded(TagsUpdatedEvent event) {
        TourDataManager.clearAndBroadcastRegister();
        if (event.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
            ItemTourGuide.clearAndBroadcastRegister();
        } else {
            Network.INSTANCE.send(PacketDistributor.SERVER.noArg(), new C2SRequestTriggerableItems());
        }
    }
}
