package net.sleepykairo.piratemod.client;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

import java.util.Optional;

public class ModModels {
    public static final Model HANDHELD_FLIPPED = ModModels.item("handheld_flipped", TextureKey.LAYER0);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(PirateMod.MOD_ID, "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
