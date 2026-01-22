package net.sodium.sodiumworld.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.sodium.sodiumworld.SodiumWorld;

public class ModSounds {
    public static final SoundEvent HORNY = registerSoundEvent("horny_groan");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(SodiumWorld.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        SodiumWorld.LOGGER.info("Registering Mod Sounds for " + SodiumWorld.MOD_ID);
    }
}
