package bcsmp.chemthunder.herald.effect;

import bcsmp.chemthunder.herald.index.HeraldEffects;
import bcsmp.chemthunder.herald.index.HeraldItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoundEffect extends StatusEffect {
    public BoundEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        BlockState state = entity.getBlockStateAtPos();

        if (entity instanceof PlayerEntity player) {
                player.slowMovement(state, new Vec3d(1.5, 1.5, 1.5));


        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 2;
        return duration % i == 0;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.playSound(SoundEvents.BLOCK_CHAIN_FALL);

        super.onApplied(entity, amplifier);
    }
}
