package bcsmp.chemthunder.herald.effect;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StrainedEffect extends StatusEffect {
    public StrainedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        BlockState state = entity.getBlockStateAtPos();

        if (entity instanceof PlayerEntity player) {
            player.slowMovement(state, new Vec3d(1, 0.2, 1));
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {


        super.onRemoved(attributeContainer);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 2;
        return duration % i == 0;
    }

    @Override
    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return super.createParticle(effect);
    }
}
