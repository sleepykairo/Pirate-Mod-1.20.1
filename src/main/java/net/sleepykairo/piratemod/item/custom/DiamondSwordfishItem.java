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
/*        int firstSharpnessBonus = 0;
        if (sharpnessLevel > 0) {
            firstSharpnessBonus = 1;
        }
        target.damage(target.getDamageSources().mobAttack(attacker), ((0.75f * sharpnessLevel) + firstSharpnessBonus) + 7.5f);*/
        switch (sharpnessLevel) {
            case 1:
                target.damage(target.getDamageSources().mobAttack(attacker), 8.5f);
                break;
            case 2:
                target.damage(target.getDamageSources().mobAttack(attacker), 9.25f);
                break;
            case 3:
                target.damage(target.getDamageSources().mobAttack(attacker), 10f);
                break;
            case 4:
                target.damage(target.getDamageSources().mobAttack(attacker), 10.75f);
                break;
            case 5:
                target.damage(target.getDamageSources().mobAttack(attacker), 11.5f);
                break;
        }
        return super.postHit(stack, target, attacker);
    }
}
