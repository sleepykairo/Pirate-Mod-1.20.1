package net.sleepykairo.piratemod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.sleepykairo.piratemod.world.StormManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityStormMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickInject(CallbackInfo ci) {
//        PlayerEntity player = (PlayerEntity) (Object) this;
//        StormManager.tickStorm(player, player.getPos());
    }
}
