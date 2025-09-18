package studio.fantasyit.tour_guide.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import studio.fantasyit.tour_guide.client.event.ClientInputEvent;

public class ClientMessages {
    public static void sendOp(boolean allowSkip, boolean noCondition) {
        MutableComponent t;
        if (noCondition)
            t = Component.translatable("message.tour_guide.next_step_tip_no_condition", ClientInputEvent.KEY_CHECK_STEP.get().getKey().getDisplayName()).withStyle(ChatFormatting.GRAY);
        else
            t = Component.translatable("message.tour_guide.next_step_tip", ClientInputEvent.KEY_CHECK_STEP.get().getKey().getDisplayName()).withStyle(ChatFormatting.GRAY);
        if (allowSkip)
            t = t.append(Component.translatable("message.tour_guide.skip_step_tip", ClientInputEvent.KEY_SKIP.get().getKey().getDisplayName()).withStyle(ChatFormatting.GRAY));
        Minecraft.getInstance().player.sendSystemMessage(t);
    }
}
