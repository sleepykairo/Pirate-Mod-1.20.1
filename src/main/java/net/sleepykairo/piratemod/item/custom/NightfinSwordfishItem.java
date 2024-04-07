package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class NightfinSwordfishItem extends SwordItem {
    public NightfinSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int sharpnessLevel = EnchantmentHelper.getLevel(Enchantments.SHARPNESS, stack);
        float targetHealth = target.getHealth();
        float finalDamage = (targetHealth / 2) + (sharpnessLevel * 0.5f) + 4;
        if (finalDamage > 12) {
            finalDamage = 12;
        }
        target.damage(target.getDamageSources().mobAttack(attacker), finalDamage);

        return super.postHit(stack, target, attacker);
    }
}
