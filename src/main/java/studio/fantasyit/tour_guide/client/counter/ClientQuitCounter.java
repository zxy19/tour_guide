package studio.fantasyit.tour_guide.client.counter;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.PacketDistributor;
import studio.fantasyit.tour_guide.network.C2SInteractTourGuideData;
import studio.fantasyit.tour_guide.network.Network;
import studio.fantasyit.tour_guide.screen.BlockHoldKeyScreen;

public class ClientQuitCounter {
    private static final long TIME_OUT = 900;
    public static long startTimeStamp = 0;

    public static void tick() {
        if (startTimeStamp != 0)
            if (System.currentTimeMillis() - startTimeStamp > TIME_OUT) {
                sendQuit();
                startTimeStamp = 0;
            }
    }

    public static boolean isPressingQuitKey() {
        return startTimeStamp != 0;
    }

    public static void keyPressed() {
        startTimeStamp = System.currentTimeMillis();
    }

    public static void keyReleased() {
        startTimeStamp = 0;
        if (Minecraft.getInstance().screen instanceof BlockHoldKeyScreen) {
            Minecraft.getInstance().setScreen(null);
        }
    }

    public static Component getPressingProgressBar() {
        long hasHoldTime = System.currentTimeMillis() - startTimeStamp;
        int p1 = Math.toIntExact(hasHoldTime * 35 / TIME_OUT);
        int p2 = 35 - p1;
        StringBuilder sp1 = new StringBuilder();
        for (int i = 0; i < p1; i++) {
            sp1.append("|");
        }
        StringBuilder sp2 = new StringBuilder();
        for (int i = 0; i < p2; i++) {
            sp2.append("|");
        }
        return Component.translatable("tooltip.tour_guide.item_tour_guide.hold",
                Component.literal(sp1.toString()).withStyle(ChatFormatting.WHITE),
                Component.literal(sp2.toString()).withStyle(ChatFormatting.GRAY)
        );
    }

    private static void sendQuit() {
        Network.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.QUIT));
    }
}
