package studio.fantasyit.tour_guide.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.ClientItemTourGuideCounter;
import studio.fantasyit.tour_guide.network.C2SInteractTourGuideData;
import studio.fantasyit.tour_guide.network.Network;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public class ClientInputEvent {
    public static final Lazy<KeyMapping> KEY_CHECK_STEP = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.check_step",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_ENTER,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_SKIP = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.skip",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_QUIT = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.quit",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_ESCAPE,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_START_TOUR_GUIDE = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.start",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_SWITCH_ITEM_GUIDE = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.switch",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_TAB,
            "key.tour_guide.category"
    ));
    public static boolean pressingShiftKey = false;

    @Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBus {
        @SubscribeEvent
        public static void registerKeyMappings(final RegisterKeyMappingsEvent event) {
            event.register(KEY_CHECK_STEP.get());
            event.register(KEY_SKIP.get());
            event.register(KEY_QUIT.get());
            event.register(KEY_START_TOUR_GUIDE.get());
            event.register(KEY_SWITCH_ITEM_GUIDE.get());
        }
    }

    @SubscribeEvent
    public static void onKey(net.minecraftforge.client.event.InputEvent.Key event) {
        InputConstants.Key key = InputConstants.getKey(event.getKey(), event.getScanCode());
        if (event.getAction() == GLFW.GLFW_PRESS) {
            if (KEY_QUIT.get().getKey().equals(key)) {
                Network.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                        new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.QUIT));
            }
            if (KEY_SKIP.get().getKey().equals(key)) {
                Network.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                        new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.SKIP));
            }
            if (KEY_CHECK_STEP.get().getKey().equals(key)) {
                if ((event.getModifiers() & GLFW.GLFW_MOD_SHIFT) != 0)
                    Network.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                            new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.BACK));
                else
                    Network.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                            new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.DONE));
            }
            if (KEY_START_TOUR_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.keyPressed();
            }
            if (KEY_SWITCH_ITEM_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.offset();
            }
            if (key.getValue() == GLFW.GLFW_KEY_LEFT_SHIFT) {
                pressingShiftKey = true;
            }
        }
        if (event.getAction() == GLFW.GLFW_RELEASE) {
            if (KEY_START_TOUR_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.keyReleased();
            }
            if (key.getValue() == GLFW.GLFW_KEY_LEFT_SHIFT) {
                pressingShiftKey = false;
            }
        }
    }
}