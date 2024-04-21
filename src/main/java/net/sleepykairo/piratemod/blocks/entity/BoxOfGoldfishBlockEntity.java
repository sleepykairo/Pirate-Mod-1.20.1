package net.sleepykairo.piratemod.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BoxOfGoldfishBlockEntity extends BlockEntity {
    private int doom = 200;
    private boolean doomActive = false;
    public BoxOfGoldfishBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BOX_OF_GOLDFISH_BLOCK_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }


    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("box_of_goldfish.doom", doom);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        nbt.getInt("box_of_goldfish.doom");
    }
}
