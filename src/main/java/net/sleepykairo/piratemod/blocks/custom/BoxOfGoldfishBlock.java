package net.sleepykairo.piratemod.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.blocks.entity.AlchemyTableBlockEntity;
import net.sleepykairo.piratemod.blocks.entity.BoxOfGoldfishBlockEntity;
import net.sleepykairo.piratemod.blocks.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class BoxOfGoldfishBlock extends BlockWithEntity implements BlockEntityProvider {
    public BoxOfGoldfishBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoxOfGoldfishBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BoxOfGoldfishBlockEntity) {
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity.getType() == ModBlockEntities.BOX_OF_GOLDFISH_BLOCK_ENTITY) {

            }
        }
        return ActionResult.SUCCESS;
    }
}
