package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class DiamondSwordfishItem extends SwordItem {
    public DiamondSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int sharpnessLevel = EnchantmentHelper.getLevel(Enchantments.SHARPNESS, stack);
        if (sharpnessLevel == 1) {
            target.damage(target.getDamageSources().mobAttack(attacker), 0.25f);

        }
        else if (sharpnessLevel == 2) {
            target.damage(target.getDamageSources().mobAttack(attacker), 0.5f);
        }
        else if (sharpnessLevel == 3) {
            target.damage(target.getDamageSources().mobAttack(attacker), 0.75f);
        }
        else if (sharpnessLevel == 4) {
            target.damage(target.getDamageSources().mobAttack(attacker), 0.1f);
        }
        else if (sharpnessLevel == 5) {
            target.damage(target.getDamageSources().mobAttack(attacker), 1.5f);
        }
        return super.postHit(stack, target, attacker);
    }
}
