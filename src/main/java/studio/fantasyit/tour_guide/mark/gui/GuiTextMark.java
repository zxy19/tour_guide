package studio.fantasyit.tour_guide.mark.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.mark.IGuiMark;

public record GuiTextMark(ResourceLocation screenPredicate, Component text, int x, int y, int width,
                          int color, int background) implements IGuiMark {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(TourGuide.MODID, "gui_text");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static GuiTextMark fromNetwork(FriendlyByteBuf buf) {
        return new GuiTextMark(buf.readResourceLocation(), buf.readJsonWithCodec(ComponentSerialization.CODEC), buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeResourceLocation(screenPredicate);
        buf.writeJsonWithCodec(ComponentSerialization.CODEC, text);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(width);
        buf.writeInt(color);
        buf.writeInt(background);
    }
}
