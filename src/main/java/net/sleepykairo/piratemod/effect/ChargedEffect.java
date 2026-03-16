package net.sleepykairo.piratemod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class ChargedEffect extends StatusEffect {
    protected ChargedEffect(StatusEffectCategory category, int color) {
        // category: StatusEffectCategory - describes if the effect is helpful (BENEFICIAL), harmful (HARMFUL) or useless (NEUTRAL)
        // color: int - Color is the color assigned to the effect (in RGB)
        super(category, color);
    }

    // Called every tick to check if the effect can be applied or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the effect every tick
        return true;
    }

    // Called when the effect is applied
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().lightningBolt(), (amplifier + 1) * 2);

        if (entity.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    entity.getX(), entity.getBodyY(0.5), entity.getZ(),
                    5,
                    0.25, 0.25, 0.25,
                    1
            );
        }
    }
}