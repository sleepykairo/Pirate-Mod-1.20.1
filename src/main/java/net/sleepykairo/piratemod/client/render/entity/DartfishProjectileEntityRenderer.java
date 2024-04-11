package net.sleepykairo.piratemod.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.sleepykairo.piratemod.entity.ModEntities;
import net.sleepykairo.piratemod.entity.custom.DartfishProjectileEntity;

@Environment(value=EnvType.CLIENT)
public class DartfishProjectileEntityRenderer
        extends ProjectileEntityRenderer<DartfishProjectileEntity> {
    public static final Identifier TEXTURE = new Identifier("piratemod:textures/entity/projectiles/dartfish.png");

    public DartfishProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(DartfishProjectileEntity entity) {
        return TEXTURE;
    }
}

