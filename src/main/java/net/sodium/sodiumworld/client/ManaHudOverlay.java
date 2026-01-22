package net.sodium.sodiumworld.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.sodium.sodiumworld.util.IEntityDataSaver;

import static java.lang.Math.round;

public class ManaHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        assert MinecraftClient.getInstance().player != null;
        int mana = ((IEntityDataSaver) MinecraftClient.getInstance().player).getPersistentData().getInt("mana");
        drawContext.fill(10, 10, 110, 20, 0xFF9090FF);
        int x = mana+10;
        drawContext.fill(10, 10, x, 20, 0xFF5050FF);
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, ""+mana, 50, 20, 0xFFFFFF, true);
    }
}
