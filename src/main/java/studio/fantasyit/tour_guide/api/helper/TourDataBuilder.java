package studio.fantasyit.tour_guide.api.helper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.data.ITourDataFactory;
import studio.fantasyit.tour_guide.data.TourData;
import studio.fantasyit.tour_guide.step.ITourStepData;
import studio.fantasyit.tour_guide.step.TourStepId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TourDataBuilder {
    List<ITourStepData<?>> steps = new ArrayList<>();
    Consumer<ServerPlayer> onStart = null;
    Consumer<ServerPlayer> onFinish = null;

    public <T> void add(ITourStepData<T> build) {
        steps.add(build);
    }

    public TourDataBuilder onStart(Consumer<ServerPlayer> onStart) {
        this.onStart = onStart;
        return this;
    }

    public TourDataBuilder onFinish(Consumer<ServerPlayer> onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public <T> TourStepBuilder<T> step(TourStepId<T> id) {
        return new TourStepBuilder<>(this, id);
    }

    public TourStepBuilder<Boolean> stepBoolean(ResourceLocation id) {
        return new TourStepBuilder<>(this, new TourStepId<>(id, Boolean.class));
    }

    public TourStepBuilder<Boolean> stepAnonymous() {
        ResourceLocation id = new ResourceLocation(TourGuide.MODID, "anonymous_step/" + steps.size());
        return stepBoolean(id);
    }

    public ITourDataFactory getBuilder() {
        return (player, id) -> {
            TourData t = new TourData(steps, id, player);
            if (onStart != null)
                t.setOnFinish(() -> onStart.accept(player));
            if (onFinish != null)
                t.setOnFinish(() -> onFinish.accept(player));
            return t;
        };
    }
}
