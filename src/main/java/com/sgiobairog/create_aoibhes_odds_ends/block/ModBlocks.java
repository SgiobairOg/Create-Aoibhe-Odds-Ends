package com.sgiobairog.create_aoibhes_odds_ends.block;

import java.util.function.Supplier;

import com.sgiobairog.create_aoibhes_odds_ends.CreateAoibhesOddsEnds;
import com.sgiobairog.create_aoibhes_odds_ends.item.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

  public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateAoibhesOddsEnds.MODID);

  public static final DeferredBlock<Block> WEATHERED_IRON_GRATE = registerBlock(
    "weathered_iron_grate",
    () -> new Block(
      BlockBehaviour.Properties.of()
        .noOcclusion()
        .strength(4f)
        .requiresCorrectToolForDrops()
        .sound(SoundType.METAL)
  ));

  public static final DeferredBlock<Block> KAMIDANA = registerBlock(
    "kamidana",
    () -> new Block(
      BlockBehaviour.Properties.of()
        .noOcclusion()
        .strength(1f)
        .requiresCorrectToolForDrops()
        .sound(SoundType.WOOD)
  ));
  
  private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
    DeferredBlock<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }

  private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
    ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }

  public static void register(IEventBus eventBus) {
    BLOCKS.register(eventBus);
  }

}
