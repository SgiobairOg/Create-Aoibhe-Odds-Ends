 package com.sgiobairog.createaoibhesgrates.item;

import java.util.function.Supplier;

import com.sgiobairog.createaoibhesgrates.CreateAoibhesGrates;
import com.sgiobairog.createaoibhesgrates.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = 
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateAoibhesGrates.MODID);

    public static final Supplier<CreativeModeTab> AOIBHE_ITEMS_TAB = CREATIVE_MODE_TAB.register("aoibhe_items_tab", 
      () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUSTY_BUCKET.get()))
        .title(Component.translatable("creativetab.createaoibhesgrates.aoibhes_items"))
        .displayItems((itemDisplayParameters, output) -> {
          output.accept(ModItems.RUSTY_BUCKET);
          output.accept(ModBlocks.WEATHERED_IRON_GRATE);
        }).build());

    public static final Supplier<CreativeModeTab> AOIBHE_BLOCKS_TAB = CREATIVE_MODE_TAB.register("aoibhe_blocks_tab", 
      () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.WEATHERED_IRON_GRATE.get()))
        .withTabsBefore((ResourceLocation.fromNamespaceAndPath(CreateAoibhesGrates.MODID, "aoibhe_items_tab")))
        .title(Component.translatable("creativetab.createaoibhesgrates.aoibhes_blocks"))
        .displayItems((itemDisplayParameters, output) -> {
          output.accept(ModItems.RUSTY_BUCKET);
          output.accept(ModBlocks.WEATHERED_IRON_GRATE);
        }).build());

    public static void register(IEventBus eventBus) {
      CREATIVE_MODE_TAB.register(eventBus);
    }
}
