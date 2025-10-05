package com.sgiobairog.create_aoibhes_odds_ends.block.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.unimi.dsi.fastutil.objects.ObjectLists.EmptyList;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.FoodProperties.PossibleEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

// TODO: Look into Block Entities, add an inventory slot to manage each offering and set a timeout for each offering. When offerings are all out, revoke the blessing.

public class KamiBlock extends Block {
  public enum Offerings {
    FOOD,
    DRINK,
    FLOWERS
  };

  private static List<Holder<MobEffect>> UNDESIRABLE_EFFECTS = List.of(
    MobEffects.POISON,
    MobEffects.HUNGER,
    MobEffects.HARM
  );

  private static final Long BLESSING_DURATION = (long) 168000;

  private static Long lastOffering;

  private static HashMap<Offerings, List<Item>> offerings = new HashMap<Offerings, List<Item>>();

  public KamiBlock(Properties properties) {
    super(properties);
  }

  public static boolean isFlower(ItemStack stack) {
    if( stack.getItem() instanceof BlockItem blockItem) {
      Block block = blockItem.getBlock();
      return block instanceof FlowerBlock;
    }
    return Boolean.FALSE;
  }

  public static boolean offeringsSatisfied() {
    return offerings.get(Offerings.FOOD).size() >= 2 &&
      offerings.get(Offerings.DRINK).size() >= 1 &&
      offerings.get(Offerings.FLOWERS).size() >= 1;
  }

  public static void bestowBlessing(Player player) {

  }

  public static boolean isOfferableFood(ItemStack stack, Player player) {
    if(stack == null || player == null) {
      return Boolean.FALSE;
    }

    final FoodProperties food = stack.getItem().getFoodProperties(stack, (LivingEntity) player);

    if( food == null) {
      return Boolean.FALSE;
    }

    for (PossibleEffect effect : food.effects()) {
      MobEffectInstance instance = effect.effect();

      try {
        if(UNDESIRABLE_EFFECTS.contains(instance.getEffect())) {
          return Boolean.FALSE;
        }
      } catch (Throwable ignored) {
        // Check how to handle here
      }
    }

    return Boolean.TRUE;
  }

  public static ItemInteractionResult offeringFailed(Level level, BlockPos pos) {
    level.playSound(null, pos, SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS);
    return ItemInteractionResult.FAIL;
  }

  public static ItemInteractionResult offeringSucceeded(Level level, ItemStack stack, BlockPos pos, Player player, SoundEvent sound) {
    level.playSound(null, pos, sound, SoundSource.BLOCKS);
    stack.shrink(1);

    lastOffering = level.getGameTime();

    if(offeringsSatisfied()) {
      bestowBlessing(player);
    }
    return ItemInteractionResult.FAIL;
  }

  @Override
  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player,
      InteractionHand hand, BlockHitResult hitResult) {
    
    if(!isOfferableFood(stack, player) && !isFlower(stack)) {
      return offeringFailed(level, pos);
    }

    if(isOfferableFood(stack, player) && stack.getUseAnimation() == UseAnim.EAT) {
      offerings.get(Offerings.FOOD).add(stack.getItem());
      return offeringSucceeded(level, stack, pos, player, SoundEvents.ITEM_FRAME_ADD_ITEM);
    }

    if(isOfferableFood(stack, player) && stack.getUseAnimation() == UseAnim.DRINK) {
      offerings.get(Offerings.DRINK).add(stack.getItem());
      return offeringSucceeded(level, stack, pos, player, SoundEvents.BOTTLE_FILL);
    }

    if(isFlower(stack)) {
      offerings.get(Offerings.DRINK).add(stack.getItem());
      return offeringSucceeded(level, stack, pos, player, SoundEvents.GRASS_PLACE);
    }

    return offeringFailed(level, pos);
  }

}
