package net.sodium.sodiumworld.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.sodium.sodiumworld.SodiumWorld;
import net.sodium.sodiumworld.screen.custom.BurnerScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<BurnerScreenHandler> BURNER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(SodiumWorld.MOD_ID, "burner_screen_handler"),
                    new ExtendedScreenHandlerType<>(BurnerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        SodiumWorld.LOGGER.info("Registering Screen Handlers for " + SodiumWorld.MOD_ID);
    }
}
