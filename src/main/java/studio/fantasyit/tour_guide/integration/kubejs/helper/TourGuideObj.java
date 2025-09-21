package studio.fantasyit.tour_guide.integration.kubejs.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import studio.fantasyit.tour_guide.api.TourGuideTrigger;
import studio.fantasyit.tour_guide.api.helper.TourDataBuilder;
import studio.fantasyit.tour_guide.api.helper.TourStepBuilder;
import studio.fantasyit.tour_guide.mark.ServerScreenPredicatorMarks;
import studio.fantasyit.tour_guide.mark.gui.GuiMainTipMark;
import studio.fantasyit.tour_guide.mark.gui.GuiRectMark;
import studio.fantasyit.tour_guide.mark.gui.GuiSlotMark;
import studio.fantasyit.tour_guide.mark.gui.GuiTextMark;
import studio.fantasyit.tour_guide.mark.world.BlockMark;
import studio.fantasyit.tour_guide.mark.world.EntityMark;
import studio.fantasyit.tour_guide.mark.world.TextMark;
import studio.fantasyit.tour_guide.step.TourStepId;

import java.util.function.Function;

public class TourGuideObj {
    public static TourGuideObj INSTANCE = new TourGuideObj();

    public ResourceLocation SCREEN_PREDICATE_ALL = ServerScreenPredicatorMarks.ALL;
    public ResourceLocation SCREEN_PREDICATE_NONE = ServerScreenPredicatorMarks.NO_GUI;

    public TourDataBuilder builder() {
        return new TourDataBuilder();
    }

    public <T> TourStepBuilder<T> stepBuilder() {
        return new TourStepBuilder<T>();
    }

    public <T> TourStepId<T> id(ResourceLocation id, Function<Object, T> caster) {
        return new TourStepId<>(id, caster);
    }

    public GuiMainTipMark makeGuiMainTipMark(ResourceLocation screenPredicate, Component text, boolean allowSkip) {
        return new GuiMainTipMark(screenPredicate, text, allowSkip);
    }

    public GuiRectMark makeGuiRectMark(ResourceLocation screenPredicate, int x, int y, int width, int height, int color, int fill) {
        return new GuiRectMark(screenPredicate, x, y, width, height, color, fill);
    }

    public GuiSlotMark makeGuiSlotMark(ResourceLocation screenPredicate, int id, int color) {
        return new GuiSlotMark(screenPredicate, id, color);
    }

    public GuiTextMark makeGuiTextMark(ResourceLocation screenPredicate, Component text, int x, int y, int width, int color, int background) {
        return new GuiTextMark(screenPredicate, text, x, y, width, color, background);
    }

    public BlockMark makeBlockMark(BlockPos pos, int color, Component text) {
        return new BlockMark(pos, null, color, text);
    }

    public BlockMark makeBlockMarkWithFace(BlockPos pos, Direction face, int color, Component text) {
        return new BlockMark(pos, face, color, text);
    }

    public EntityMark makeEntityMark(int entityId, int color, Component text) {
        return new EntityMark(entityId, color, text);
    }

    public TextMark makeTextMark(Vec3 pos, Component text, int color) {
        return new TextMark(text, pos, color);
    }

    public void trigger(ServerPlayer player, String key, Object data) {
        TourGuideTrigger.trigger(player, key, data);
    }

    public void triggerNoData(ServerPlayer player, String key) {
        TourGuideTrigger.trigger(player, key);
    }

    public void triggerGlobal(String key, Object data) {
        TourGuideTrigger.trigger(key, data);
    }

    public void triggerGlobalNoData(String key) {
        TourGuideTrigger.trigger(key);
    }

    public void triggerClient(String key) {
        TourGuideTrigger.triggerClient(key);
    }
}
