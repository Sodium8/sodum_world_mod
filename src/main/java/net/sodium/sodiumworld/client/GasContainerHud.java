package net.sodium.sodiumworld.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.sodium.sodiumworld.block.entity.ImplementedGasInventory;
import net.sodium.sodiumworld.block.entity.custom.GasContainerEntity;
import net.sodium.sodiumworld.util.GasStack;

import java.util.List;

public class GasContainerHud {

    public static void register() {
        HudRenderCallback.EVENT.register(GasContainerHud::render);
    }

    private static void render(DrawContext context, RenderTickCounter tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null) return;

        HitResult hit = client.crosshairTarget;
        if (hit == null || hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;

        var blockEntity = client.world.getBlockEntity(blockHit.getBlockPos());
        if (!(blockEntity instanceof ImplementedGasInventory gasEntity)) return;

        renderGasInfo(context, gasEntity);
    }
    private static void renderGasInfo(DrawContext context, ImplementedGasInventory entity) {
        MinecraftClient client = MinecraftClient.getInstance();
        List<GasStack> gases = entity.getGasInventory();
        int width = 120;
        int height = 20 + gases.size()*6;
        int x = client.getWindow().getScaledWidth() / 2 + 10;
        int y = client.getWindow().getScaledHeight() / 2 - 10 - height;
        context.fill(x, y, x + width, y + height, 0xAA000000);
        context.drawBorder(x, y, width, height, 0xFFFFFFFF);
        context.drawText(
                client.textRenderer,
                "Gas Container",
                x + 6,
                y + 6,
                0xFFFFFF,
                false
        );
        for (int i = 0; i < gases.size(); i++) {
            context.drawText(
                    client.textRenderer,
                    gases.get(i).getId() + ": " + gases.get(i).getVolume(),
                    x + 6,
                    y + 14+i*20,
                    0xAAAAAA,
                    false
            );
        }
    }
}