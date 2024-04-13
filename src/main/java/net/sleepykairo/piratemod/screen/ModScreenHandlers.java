package net.sleepykairo.piratemod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AlchemyTableScreenHandler> ALCHEMY_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(PirateMod.MOD_ID, "alchemy_table"),
                    new ExtendedScreenHandlerType<>(AlchemyTableScreenHandler::new));

    public static void registerScreenHandlers() {
        PirateMod.LOGGER.info("Registering Screen Handlers for " + PirateMod.MOD_ID);
    }
}
