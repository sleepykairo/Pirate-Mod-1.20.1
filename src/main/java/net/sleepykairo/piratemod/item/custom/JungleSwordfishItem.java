package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.PirateMod;
import net.sleepykairo.piratemod.util.interfaces.PlayerAttackAccess;

public class JungleSwordfishItem extends SwordItem {
    public JungleSwordfishItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int poisonStack = 0;
        float cooldownProgress = 1;
        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            PlayerAttackAccess playerAttackAccess = (PlayerAttackAccess) player;

            cooldownProgress = playerAttackAccess.getPreviousAttackCooldown();
        }
        if (target.getStatusEffect(StatusEffects.POISON) != null) {
            poisonStack = target.getStatusEffect(StatusEffects.POISON).getDuration();
        }
        if (poisonStack > 300) {
            poisonStack = 300;
        }
        if (cooldownProgress > 0.9f) target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, (poisonStack + 40), 0), attacker);
        PirateMod.LOGGER.info(Float.toString(cooldownProgress));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        float cooldownProgress = user.getAttackCooldownProgress(0.5f);
        PirateMod.LOGGER.info(Float.toString(cooldownProgress));
        return super.use(world, user, hand);
    }
}
