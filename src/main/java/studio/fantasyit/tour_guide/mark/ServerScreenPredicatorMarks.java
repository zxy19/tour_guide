package studio.fantasyit.tour_guide.mark;

import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.TourGuide;

public class ServerScreenPredicatorMarks {
    public static final ResourceLocation ALL = new ResourceLocation(TourGuide.MODID, "all");
    public static final ResourceLocation NO_GUI = new ResourceLocation(TourGuide.MODID, "no_gui");

    public static ResourceLocation noTransform(ResourceLocation id) {
        return new ResourceLocation(id.getNamespace(), "no_transform/" + id.getPath());
    }

    public static ResourceLocation base(ResourceLocation id) {
        if (isNoTransform(id)) {
            return new ResourceLocation(id.getNamespace(), id.getPath().substring("no_transform/".length()));
        }
        return id;
    }

    public static boolean isNoTransform(ResourceLocation id) {
        return id.getPath().startsWith("no_transform/");
    }
}
