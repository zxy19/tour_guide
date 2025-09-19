package studio.fantasyit.tour_guide.client.event;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.MarkRendererManager;
import studio.fantasyit.tour_guide.client.TourGuidingClientData;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class LevelRenderingEvent {

    @SubscribeEvent
    public static void onLevelRender(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES)
            return;
        TourGuidingClientData.getMarks().forEach(mark -> {
            MarkRendererManager.dispatchWorldRender(
                    mark,
                    Minecraft.getInstance().renderBuffers().bufferSource(),
                    event.getCamera(),
                    event.getLevelRenderer(),
                    event.getPoseStack(),
                    event.getPartialTick()
            );
        });
    }
}
