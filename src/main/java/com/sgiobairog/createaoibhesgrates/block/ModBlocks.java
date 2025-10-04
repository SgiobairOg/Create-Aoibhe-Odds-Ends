package com.sgiobairog.createaoibhesgrates.block;

import java.util.function.Supplier;

import com.sgiobairog.createaoibhesgrates.CreateAoibhesGrates;
import com.sgiobairog.createaoibhesgrates.item.ModItems;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

  public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateAoibhesGrates.MODID);

  public static final DeferredBlock<Block> WEATHERED_IRON_GRATE = registerBlock(
    "weathered_iron_grate",
    () -> new Block(
      BlockBehaviour.Properties.of()
        .strength(4f)
        .requiresCorrectToolForDrops()
        .sound(SoundType.IRON)
  ));
  
  private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
    DeferredBlock<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }

  private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
    ModItems.ITEMS.registerItem(
      "rusty_bucket",
      Item::new,
      new Item.Properties()
    );
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }

}
