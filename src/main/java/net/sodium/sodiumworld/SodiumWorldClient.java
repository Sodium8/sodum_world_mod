package net.sodium.sodiumworld;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.sodium.sodiumworld.block.ModBlocks;
import net.sodium.sodiumworld.block.entity.ModBlockEntities;
import net.sodium.sodiumworld.block.entity.rederer.DishBlockEntityRenderer;
import net.sodium.sodiumworld.client.ManaHudOverlay;
import net.sodium.sodiumworld.entity.ModEntities;
import net.sodium.sodiumworld.entity.client.CarrotCar.CarrotCarModel;
import net.sodium.sodiumworld.entity.client.CarrotCar.CarrotCarRenderer;
import net.sodium.sodiumworld.entity.client.Dildo.DildoRenderer;
import net.sodium.sodiumworld.entity.client.Hitler.HitlerModel;
import net.sodium.sodiumworld.entity.client.Hitler.HitlerRenderer;
import net.sodium.sodiumworld.entity.client.Portal.PortalModel;
import net.sodium.sodiumworld.entity.client.Portal.PortalRenderer;
import net.sodium.sodiumworld.entity.client.jopa.JopaModel;
import net.sodium.sodiumworld.entity.client.jopa.JopaRenderer;
import net.sodium.sodiumworld.event.KeyInputHandler;
import net.sodium.sodiumworld.networking.ModMessages;

public class SodiumWorldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PENIS_CROP, RenderLayer.getCutout());
        EntityRendererRegistry.register(ModEntities.DILDO, DildoRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, DishBlockEntityRenderer::new);
        KeyInputHandler.register();
        HudRenderCallback.EVENT.register(new ManaHudOverlay());
        ModMessages.registerS2CPackets();
        EntityModelLayerRegistry.registerModelLayer(CarrotCarModel.CAR_CARROT, CarrotCarModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CARROT_CAR, CarrotCarRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(HitlerModel.HITLER, HitlerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HITLER, HitlerRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(PortalModel.PORTAL, PortalModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PORTAL, PortalRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(JopaModel.JOPA, JopaModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JOPA, JopaRenderer::new);
    }
}
