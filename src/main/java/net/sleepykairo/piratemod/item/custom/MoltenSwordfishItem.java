package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class MoltenSwordfishItem extends SwordItem {
    public MoltenSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int fireAspectLevel = EnchantmentHelper.getFireAspect(attacker);
        target.setOnFireFor(5 + (fireAspectLevel * 5));

        return super.postHit(stack, target, attacker);
    }
}
