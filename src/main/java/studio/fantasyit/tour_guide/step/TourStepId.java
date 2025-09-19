package studio.fantasyit.tour_guide.step;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public record TourStepId<T>(ResourceLocation id, Function<Object, T> caster) {
    public TourStepId(ResourceLocation id, Class<T> caster) {
        this(id, t -> {
            if (t.getClass().equals(caster))
                return caster.cast(t);
            return null;
        });
    }

    public T cast(Object obj) {
        if (obj == null)
            return null;
        try {
            return caster.apply(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
