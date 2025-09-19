package studio.fantasyit.tour_guide.step;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public record TourStepId<T>(ResourceLocation id, Function<Object, T> caster) {
    public TourStepId(ResourceLocation id, Class<T> caster) {
        this(id, caster::cast);
    }

    public T cast(Object obj) {
        return caster.apply(obj);
    }
}
