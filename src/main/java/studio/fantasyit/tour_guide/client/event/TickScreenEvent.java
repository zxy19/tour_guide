package studio.fantasyit.tour_guide.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.ClientItemTourGuideCounter;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class TickScreenEvent {
    @SubscribeEvent
    public static void onTick(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> s && s.getSlotUnderMouse() != null) {
            ClientItemTourGuideCounter.updateHoveredItem(s.getSlotUnderMouse().getItem().getItem());
        } else {
            ClientItemTourGuideCounter.updateHoveredItem(null);
        }
    }
}
