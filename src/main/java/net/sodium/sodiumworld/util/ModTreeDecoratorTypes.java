package net.sodium.sodiumworld.util;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.sodium.sodiumworld.SodiumWorld;

public class ModTreeDecoratorTypes {
    public static final TreeDecoratorType<LemonFruitDecorator> LEMON_FRUIT =
            Registry.register(Registries.TREE_DECORATOR_TYPE,
                    Identifier.of(SodiumWorld.MOD_ID, "lemon_fruit"),
                    new TreeDecoratorType<>(LemonFruitDecorator.CODEC));
    public static void register() {
        SodiumWorld.LOGGER.info("Registered custom tree decorators");
    }
}
