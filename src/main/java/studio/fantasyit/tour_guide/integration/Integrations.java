package studio.fantasyit.tour_guide.integration;

import net.minecraftforge.fml.ModList;

public class Integrations {
    public static boolean kjs() {
        return ModList.get().isLoaded("kubejs");
    }
}
