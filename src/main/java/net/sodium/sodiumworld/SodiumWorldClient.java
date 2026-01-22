package net.sodium.sodiumworld;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.rederer.DishBlockEntityRenderer;
import net.sodium.sodiumworld.client.ManaHudOverlay;
import net.sodium.sodiumworld.entity.ModEntities;
import net.sodium.sodiumworld.entity.client.DildoRenderer;
import net.sodium.sodiumworld.event.KeyInputHandler;
import net.sodium.sodiumworld.networking.ModMessages;
import net.sodium.sodiumworld.networking.packet.SpawnPenisC2SPacket;
import net.sodium.sodiumworld.networking.packet.SyncManaS2CPacket;

public class SodiumWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PENIS_CROP, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.DILDO, DildoRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, DishBlockEntityRenderer::new);
        KeyInputHandler.register();
        HudRenderCallback.EVENT.register(new ManaHudOverlay());
        ModMessages.registerS2CPackets();
    }
}
