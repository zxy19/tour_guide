package studio.fantasyit.tour_guide.step;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import studio.fantasyit.tour_guide.data.TourData;
import studio.fantasyit.tour_guide.mark.IMark;
import studio.fantasyit.tour_guide.network.Network;
import studio.fantasyit.tour_guide.network.S2CTipMessage;

import java.util.List;

public interface ITourStepData<T> {
    TourStepId<T> getId();

    @Nullable List<Component> getChatText(TourData data);

    default boolean shouldSkipAtStart(TourData data) {
        return false;
    }

    List<IMark> init(TourData data);

    @Nullable Component getUnfinishReason(TourData data);

    default boolean checkIfFinished(TourData data) {
        return getUnfinishReason(data) == null;
    }

    default boolean allowSkip() {
        return false;
    }

    default void skipped(TourData data) {
    }

    T finish(TourData data);

    default boolean receiveTrigger(String key) {
        return false;
    }

    default void sendExtraTip(TourData tourData) {
        PacketDistributor.sendToPlayer(tourData.getPlayer(), new S2CTipMessage(allowSkip(),false));
    }
}
