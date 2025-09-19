package studio.fantasyit.tour_guide.mark.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.mark.IMark;

public record BlockMark(BlockPos pos, @Nullable Direction face, int color, Component text) implements IMark {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(TourGuide.MODID, "block");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static BlockMark fromNetwork(FriendlyByteBuf buf) {
        return new BlockMark(buf.readBlockPos(), buf.readBoolean() ? buf.readEnum(Direction.class) : null, buf.readInt(), buf.readJsonWithCodec(ComponentSerialization.CODEC));
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeBoolean(face != null);
        if (face != null) buf.writeEnum(face);
        buf.writeInt(color);
        buf.writeJsonWithCodec(ComponentSerialization.CODEC, text);
    }
}