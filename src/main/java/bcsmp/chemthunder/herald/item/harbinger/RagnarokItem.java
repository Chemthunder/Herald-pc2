package bcsmp.chemthunder.herald.item.harbinger;

import bcsmp.chemthunder.herald.index.HeraldEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class RagnarokItem extends Item {
    public RagnarokItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockPos pos = user.getBlockPos();

        net.minecraft.util.math.Box area = new Box(pos).expand(20);
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, area,
                entity -> true
        );

        for (LivingEntity entity : entities) {
            if (!entity.getOffHandStack().isOf(Items.ACACIA_FENCE)) {
                entity.addStatusEffect(new StatusEffectInstance(HeraldEffects.STRAINED, 320));
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(ParticleTypes.END_ROD, pos.getX(), pos.getY() + 1.5, pos.getZ(),
                            50,
                            1.5,
                            0.5,
                            1.5,
                            0.02
                    );
                }
            }
        }
        return super.use(world, user, hand);
    }


}
