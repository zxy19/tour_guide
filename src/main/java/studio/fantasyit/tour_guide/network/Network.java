package studio.fantasyit.tour_guide.network;

import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import studio.fantasyit.tour_guide.TourGuide;

public class Network {
    @EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class Event {
        @SubscribeEvent
        public static void regis(RegisterPayloadHandlersEvent event) {
            PayloadRegistrar registrar = event.registrar("1");
            registrar.playToClient(S2CSyncTriggerableItems.TYPE, StreamCodec.of(
                    (t, b) -> b.toNetwork(t),
                    S2CSyncTriggerableItems::fromNetwork
            ), S2CSyncTriggerableItems::handle);
            registrar.playToClient(S2CTipMessage.TYPE, StreamCodec.of(
                    (t, b) -> b.toNetwork(t),
                    S2CTipMessage::fromNetwork
            ), S2CTipMessage::handle);
            registrar.playToClient(S2CUpdateTourGuideData.TYPE,
                    StreamCodec.of(
                            (t, b) -> b.toNetwork(t),
                            S2CUpdateTourGuideData::fromNetwork
                    ), S2CUpdateTourGuideData::handle
            );
            registrar.playToServer(C2SClientTrigger.TYPE,
                    StreamCodec.of(
                            (t, b) -> b.toNetwork(t),
                            C2SClientTrigger::fromNetwork
                    ), C2SClientTrigger::handle
            );
            registrar.playToServer(C2SInteractTourGuideData.TYPE,
                    StreamCodec.of(
                            (t, b) -> b.toNetwork(t),
                            C2SInteractTourGuideData::fromNetwork
                    ), C2SInteractTourGuideData::handle
            );
            registrar.playToServer(C2SStartTourGuide.TYPE,
                    StreamCodec.of(
                            (t, b) -> b.toNetwork(t),
                            C2SStartTourGuide::fromNetwork
                    ), C2SStartTourGuide::handle
            );
            registrar.playToServer(C2SRequestTriggerableItems.TYPE,
                    StreamCodec.of(
                            (t, b) -> b.toNetwork(t),
                            C2SRequestTriggerableItems::fromNetwork
                    ), C2SRequestTriggerableItems::handle
            );
        }
    }
}
