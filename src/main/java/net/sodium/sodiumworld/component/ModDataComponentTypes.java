package net.sodium.sodiumworld.component;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.util.GasStack;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<BlockPos> COORDINATES =
            register("coordinates", builder -> builder.codec(BlockPos.CODEC));

    public static final ComponentType<List<GasStack>> GAS =
            register("gas_containing", builder -> builder.codec(GasStack.CODEC.listOf()));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(SodiumWorld.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }
    public static void registerDataComponentTypes() {
        SodiumWorld.LOGGER.info("registering Data Component Types for "+ SodiumWorld.MOD_ID);
    }
}
