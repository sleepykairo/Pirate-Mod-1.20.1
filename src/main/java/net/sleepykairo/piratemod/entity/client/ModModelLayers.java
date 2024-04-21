package net.sleepykairo.piratemod.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

public class ModModelLayers {
    public static final EntityModelLayer BLOKE =
            new EntityModelLayer(new Identifier(PirateMod.MOD_ID, "bloke"), "main");

    public static final EntityModelLayer GHOST =
            new EntityModelLayer(new Identifier(PirateMod.MOD_ID, "ghost"), "main");
}
