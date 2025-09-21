package studio.fantasyit.tour_guide.integration.kubejs.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DataHelpers {
    public static DataHelpers INSTANCE = new DataHelpers();

    public int rgba2argb(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public int hex2argb(long hex) {
        return (int) hex;
    }

    public <T> List<T> list(T[] arr) {
        return List.of(arr);
    }


    public BlockPos pos(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    public Vec3 vec(double x, double y, double z) {
        return new Vec3(x, y, z);
    }

    public Component text(String text) {
        return Component.literal(text);
    }

    public Component textA(String text, Object... args) {
        return Component.translatable(text, args);
    }

    public Component translatable(String key) {
        return Component.translatable(key);
    }
    public Component translatableA(String key, Object... args) {
        return Component.translatable(key, args);
    }
}
