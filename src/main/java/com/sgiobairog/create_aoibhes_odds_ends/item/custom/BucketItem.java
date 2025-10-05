package com.sgiobairog.create_aoibhes_odds_ends.item.custom;

import java.util.Map;

import com.sgiobairog.create_aoibhes_odds_ends.block.ModBlocks;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BucketItem extends Item {
  private static final Map<Block, Block> BUCKET_MAP = 
    Map.of(
      Blocks.STONE, Blocks.STONE_BRICKS,
      Blocks.SANDSTONE, ModBlocks.WEATHERED_IRON_GRATE.get()
    );
  
  public BucketItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

    if(BUCKET_MAP.containsKey(clickedBlock)) {
      if(!level.isClientSide()) {
        level.setBlockAndUpdate(context.getClickedPos(), BUCKET_MAP.get(clickedBlock).defaultBlockState());

        context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(), item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

        level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
      }
    }
    
    return InteractionResult.SUCCESS;
  }
}
