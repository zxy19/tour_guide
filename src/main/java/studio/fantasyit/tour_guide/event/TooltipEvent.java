package studio.fantasyit.tour_guide.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.ClientItemTourGuideCounter;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TooltipEvent {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ClientItemTourGuideCounter.addTooltip(event.getItemStack().getItem(), event.getToolTip());
    }
}
