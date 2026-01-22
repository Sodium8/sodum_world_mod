package net.sodium.sodiumworld.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.sodium.sodiumworld.networking.packet.SpawnPenisC2SPacket;
import net.sodium.sodiumworld.sound.ModSounds;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_SODIUM = "key.category.sodiumworld";
    public static final String KEY_PLAY_SOUND = "key.sodiumworld.play_sound";
    public static KeyBinding playSound;
    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (playSound.wasPressed()){
                assert client.world != null;
                assert client.player != null;
                ClientPlayNetworking.send(new SpawnPenisC2SPacket());
                client.world.playSound(client.player, client.player.getBlockPos(), ModSounds.HORNY, SoundCategory.BLOCKS, 1f, 1f);
            }
        });
    }
    public static void register(){
        playSound = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_PLAY_SOUND,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_SODIUM
        ));
        registerKeyInputs();
    }
}
