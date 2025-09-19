package studio.fantasyit.tour_guide.integration.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import studio.fantasyit.maid_storage_manager.integration.kubejs.binding.*;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.base.BaseSupplierWrapper;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.base.BaseWrappedWrapper;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.context.IKJSCraftContext;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.context.KJSCraftContext;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.contextSupplier.IKJSCraftContextSupplier;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.contextSupplier.KJSCraftContextSupplier;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.generator.IKJSAutoCraftGuideGenerator;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.generator.KJSAutoCraftGuideGenerator;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.type.IKJSCraftType;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.craft.type.KJSCraftType;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.item.KJSItemPair;
import studio.fantasyit.maid_storage_manager.integration.kubejs.wrapped.item.KJSItemPairWrapper;

public class KJSPlugin extends KubeJSPlugin {

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
    }

    @Override
    public void registerEvents() {
        KJSRegEvent.client.register();
        KJSRegEvent.common.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
    }
}
