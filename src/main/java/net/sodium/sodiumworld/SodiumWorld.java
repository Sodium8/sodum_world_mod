package net.sodium.sodiumworld;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.component.ModDataComponentTypes;
import net.sodium.sodiumworld.item.ModItemGroups;
import net.sodium.sodiumworld.item.ModItems;
import net.sodium.sodiumworld.sound.ModSounds;
import net.sodium.sodiumworld.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SodiumWorld implements ModInitializer {
	public static final String MOD_ID = "sodium-world";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FireFrighting.register();
		ModSounds.registerSounds();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModDataComponentTypes.registerDataComponentTypes();
		CompostingChanceRegistry.INSTANCE.add(ModItems.PENIS_SEEDS, 0.2f);
		ModWorldGeneration.generateModWorldGen();
	}
}