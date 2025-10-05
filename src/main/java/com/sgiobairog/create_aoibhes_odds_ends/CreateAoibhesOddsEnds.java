package com.sgiobairog.create_aoibhes_odds_ends;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.sgiobairog.create_aoibhes_odds_ends.block.ModBlocks;
import com.sgiobairog.create_aoibhes_odds_ends.item.ModCreativeModeTabs;
import com.sgiobairog.create_aoibhes_odds_ends.item.ModItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateAoibhesOddsEnds.MODID)
public class CreateAoibhesOddsEnds {
    public static final String MODID = "create_aoibhes_odds_ends";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateAoibhesOddsEnds(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
      if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
        event.accept(ModItems.RUSTY_BUCKET);
      }

      if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
        event.accept(ModBlocks.KAMIDANA);
      }

      if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
        event.accept(ModBlocks.WEATHERED_IRON_GRATE);
      }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        
    }
}
