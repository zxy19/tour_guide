package studio.fantasyit.tour_guide.integration;


import net.neoforged.fml.ModList;

public class Integrations {
    public static boolean kjs() {
        return ModList.get().isLoaded("kubejs");
    }
}
