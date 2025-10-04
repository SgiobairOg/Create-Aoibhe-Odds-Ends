package com.sgiobairog.createaoibhesgrates.item;

import com.sgiobairog.createaoibhesgrates.CreateAoibhesGrates;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateAoibhesGrates.MODID);

  public static final DeferredItem<Item> RUSTY_BUCKET = ITEMS.register(
    "rusty_bucket",
    () -> new Item(new Item.Properties())
  );

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
