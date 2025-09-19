package studio.fantasyit.tour_guide.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.MarkRendererManager;
import studio.fantasyit.tour_guide.client.TourGuidingClientData;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class GuiRenderingEvent {

    @SubscribeEvent
    public static void onGuiRender(RenderGuiEvent.Post event) {
        Screen screen = Minecraft.getInstance().screen;
        if (screen == null)
            TourGuidingClientData.getMarks().forEach(mark -> {
                MarkRendererManager.dispatchGuiRender(mark, event.getGuiGraphics(), screen);
            });
    }

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        Screen screen = event.getScreen();
        TourGuidingClientData.getMarks().forEach(mark -> {
            MarkRendererManager.dispatchGuiRender(mark, event.getGuiGraphics(), screen);
        });
    }
}
