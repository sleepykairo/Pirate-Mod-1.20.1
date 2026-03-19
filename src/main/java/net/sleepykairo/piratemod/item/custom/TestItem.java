package net.sleepykairo.piratemod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;

public class TestItem extends Item {
    private boolean test = false;

    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            this.test = !this.test;
            PirateMod.LOGGER.info("test: {}", this.test);
        }
        return super.use(world, user, hand);
    }
}
