package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class JungleSwordfishItem extends SwordItem {
    public JungleSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int poisonStack = 0;
        if (target.getStatusEffect(StatusEffects.POISON) != null) {
            poisonStack = target.getStatusEffect(StatusEffects.POISON).getDuration();
        }

        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (poisonStack + 40), 1), attacker);

        return super.postHit(stack, target, attacker);
    }
}
