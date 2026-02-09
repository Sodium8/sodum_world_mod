package net.sodium.sodiumworld;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.component.ModDataComponentTypes;
import net.sodium.sodiumworld.entity.ModEntities;
import net.sodium.sodiumworld.entity.custom.CarrotCarEntity;
import net.sodium.sodiumworld.entity.custom.HitlerEntity;
import net.sodium.sodiumworld.event.PlayerTickHandler;
import net.sodium.sodiumworld.item.ModItemGroups;
import net.sodium.sodiumworld.item.ModItems;
import net.sodium.sodiumworld.networking.ModMessages;
import net.sodium.sodiumworld.networking.packet.ChangeJopaRootPosS2CPacket;
import net.sodium.sodiumworld.networking.packet.SpawnPenisC2SPacket;
import net.sodium.sodiumworld.networking.packet.SyncGasS2CPacket;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;
import net.sodium.sodiumworld.screen.ModScreenHandlers;
import net.sodium.sodiumworld.sound.ModSounds;
import net.sodium.sodiumworld.util.ModTreeDecoratorTypes;
import net.sodium.sodiumworld.world.CustomModFeatures;
import net.sodium.sodiumworld.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SodiumWorld implements ModInitializer {
	public static final String MOD_ID = "sodium-world";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CustomModFeatures.registerFeatures();
		FireFrighting.register();
		ModSounds.registerSounds();
		PayloadTypeRegistry.playC2S().register(SpawnPenisC2SPacket.ID, SpawnPenisC2SPacket.CODEC);
		PayloadTypeRegistry.playS2C().register(SyncManaS2CPacket.ID, SyncManaS2CPacket.CODEC);
		PayloadTypeRegistry.playS2C().register(ChangeJopaRootPosS2CPacket.ID, ChangeJopaRootPosS2CPacket.CODEC);
		PayloadTypeRegistry.playS2C().register(SyncGasS2CPacket.ID, SyncGasS2CPacket.CODEC);
		ModMessages.registerC2SPackets();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModDataComponentTypes.registerDataComponentTypes();
		CompostingChanceRegistry.INSTANCE.add(ModItems.PENIS_SEEDS, 0.2f);
		ModWorldGeneration.generateModWorldGen();
		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
		ModTreeDecoratorTypes.register();
		FabricDefaultAttributeRegistry.register(ModEntities.CARROT_CAR, CarrotCarEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HITLER, HitlerEntity.createAttributes());
	}
}