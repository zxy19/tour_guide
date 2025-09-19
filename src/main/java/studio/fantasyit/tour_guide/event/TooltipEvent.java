package studio.fantasyit.tour_guide.event;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.counter.ClientItemTourGuideCounter;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TooltipEvent {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ClientItemTourGuideCounter.addTooltip(event.getItemStack().getItem(), event.getToolTip());
    }
}
