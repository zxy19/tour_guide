package studio.fantasyit.tour_guide.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.event.ClientMarkRendererRegisterEvent;
import studio.fantasyit.tour_guide.api.event.ScreenPredicatorRegisterEvent;
import studio.fantasyit.tour_guide.integration.Integrations;
import studio.fantasyit.tour_guide.integration.kubejs.KubeJSPort;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SetupEvent {
    @SubscribeEvent
    public static void onSetup(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            ModLoader.get().postEvent(new ClientMarkRendererRegisterEvent());
            ModLoader.get().postEvent(new ScreenPredicatorRegisterEvent());
            if (Integrations.kjs())
                KubeJSPort.setupClient();
        });
    }
}
