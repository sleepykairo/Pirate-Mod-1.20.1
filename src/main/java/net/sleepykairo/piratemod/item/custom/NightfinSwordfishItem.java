package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class NightfinSwordfishItem extends SwordItem {
    public NightfinSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0, false, false));

        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName(ItemStack stack) {
        MutableText text = Text.translatable(this.getTranslationKey(stack));
//        text = stack.hasEnchantments() ? text.formatted(Formatting.Bl) : text.formatted(Formatting.RED);
//        text.setStyle(Style.of());
        return super.getName(stack);
    }
}
