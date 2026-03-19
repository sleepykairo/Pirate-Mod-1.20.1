package net.sleepykairo.piratemod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.PirateMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MUSKET_BALL_BREAKABLE = createTag("musket_ball_breakable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(PirateMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HELD_LIKE_CROSSBOW = createTag("held_like_crossbow");
        public static final TagKey<Item> HELD_LIKE_CROSSBOW_UNLOADED = createTag("held_like_crossbow_unloaded");

        public static final TagKey<Item> IGNORE_ENCHANTED_NAME_FORMATTING = createTag("ignore_enchanted_name_formatting");
        public static final TagKey<Item> GLINT_WITHOUT_ENCHANTMENT = createTag("glint_without_enchantment");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(PirateMod.MOD_ID, name));
        }
    }
}
