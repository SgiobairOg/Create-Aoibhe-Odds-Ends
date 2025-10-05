package com.sgiobairog.create_aoibhes_odds_ends.item;

import com.sgiobairog.create_aoibhes_odds_ends.CreateAoibhesOddsEnds;
import com.sgiobairog.create_aoibhes_odds_ends.item.custom.BucketItem;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateAoibhesOddsEnds.MODID);

  public static final DeferredItem<Item> RUSTY_BUCKET = ITEMS.register(
    "rusty_bucket",
    () -> new Item(new Item.Properties())
  );

  public static final DeferredItem<Item> MAGIC_BUCKET = ITEMS.register(
    "magic_bucket", 
    () -> new BucketItem(new Item.Properties().durability(32))
  );

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
