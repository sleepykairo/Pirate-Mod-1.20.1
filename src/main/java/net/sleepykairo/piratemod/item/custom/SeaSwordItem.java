package net.sleepykairo.piratemod.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sleepykairo.piratemod.effect.ModEffects;

import java.util.List;

public class SeaSwordItem extends SwordItem {
    public SeaSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        float radius = 2.5f;
        float particleRadius = 2;
        List<LivingEntity> hitEntitiesList = world.getNonSpectatingEntities(
                LivingEntity.class, user.getBoundingBox().expand(radius, 0.25, radius));

        for (LivingEntity entity : hitEntitiesList) {
            if (entity == user || entity.isTeammate(user)) continue;
            entity.damage(user.getDamageSources().playerAttack(user), 6);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0));

            knockBack(entity, user);
        }

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.BUBBLE_POP,
                    user.getX(), user.getBodyY(0), user.getZ(),
                    150,
                    particleRadius, 0.25, particleRadius,
                    0.1
            );
            serverWorld.spawnParticles(
                    ParticleTypes.FISHING,
                    user.getX(), user.getBodyY(0), user.getZ(),
                    150,
                    particleRadius, 0.25, particleRadius,
                    0.25
            );
        }
        user.playSound(SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.PLAYERS,
                0.5f, (user.getRandom().nextFloat()) * 0.5f + 1.0f);

        user.getItemCooldownManager().set(this, 150);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    private void knockBack(Entity target, LivingEntity attacker) {
        double d = target.getX() - attacker.getX();
        double e = target.getZ() - attacker.getZ();
        double f = Math.max(d * d + e * e, 0.001);

        double mult = (double) 1 / 3;
        double multY = 0.125;
        double kbMult = 1;

        double kb = (EnchantmentHelper.getKnockback(attacker) * kbMult) + 1;

        target.addVelocity(d / f * mult * kb, (multY * kb) + 0.25, e / f * mult * kb);
    }
}
