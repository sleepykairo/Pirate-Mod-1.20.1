package net.sleepykairo.piratemod.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.entity.custom.DartfishProjectileEntity;
import net.sleepykairo.piratemod.entity.custom.PoisonDartfishProjectileEntity;

@Environment(value=EnvType.CLIENT)
public class PoisonDartfishProjectileEntityRenderer
        extends ProjectileEntityRenderer<PoisonDartfishProjectileEntity> {
    public static final Identifier TEXTURE = new Identifier("piratemod:textures/entity/projectiles/poison_dartfish.png");

    public PoisonDartfishProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PoisonDartfishProjectileEntity entity) {
        return TEXTURE;
    }
}

