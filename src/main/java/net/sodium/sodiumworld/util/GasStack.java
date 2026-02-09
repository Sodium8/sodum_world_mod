package net.sodium.sodiumworld.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class GasStack {
    public static final Codec<GasStack> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(GasStack::getId),
            Codec.FLOAT.fieldOf("volume").forGetter(GasStack::getVolume)
    ).apply(instance, GasStack::new));
    private float volume;
    private final String id;
    public GasStack(String id, float volume){
        this.id = id;
        this.volume = volume;
    }

    public String getId() {
        return id;
    }

    public float getVolume() {
        return volume;
    }
    public void add_Volume(float amount){
        this.volume += amount;
    }
}
