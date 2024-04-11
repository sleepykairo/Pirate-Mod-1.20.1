package net.sleepykairo.piratemod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.screen.Property;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Properties;

public class GardenersHoeItem extends HoeItem {
    public GardenersHoeItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        BlockState defaultState = state.getBlock().getDefaultState();
        Block currentBlock = state.getBlock();


        if (currentBlock.getStateWithProperties(state) != defaultState) {
            if (currentBlock == Blocks.WHEAT && state.get(CropBlock.AGE) == 7
                    || currentBlock == Blocks.CARROTS && state.get(CropBlock.AGE) == 7
                    || currentBlock == Blocks.POTATOES && state.get(CropBlock.AGE) == 7
                    || currentBlock == Blocks.NETHER_WART && state.get(CropBlock.AGE) == 3) {
                world.setBlockState(pos, defaultState);
                stack.damage(1, miner, null);
            }
        }

        return super.postMine(stack, world, state, pos, miner);
    }
}
