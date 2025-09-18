package bcsmp.chemthunder.herald.block;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.index.HeraldEffects;
import bcsmp.chemthunder.herald.index.HeraldItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacredEffigyBlock extends Block {
    public SacredEffigyBlock(Settings settings) {
        super(settings);
    }


    private static final VoxelShape SHAPE = Block.createCuboidShape(
            2.0, 0.0, 2.0,
            14.0, 16.0, 14.0
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 0);
            BlockPos abovePos = pos.up();


            if (world.getBlockState(abovePos).isAir()) {
                world.setBlockState(abovePos, state);
            }
        }
        placer.playSound(SoundEvents.ENTITY_WITHER_SPAWN, 1, 1);
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD ,pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, 0.1, 3, 0.1, 0.05);
        }
        super.onBroken(world, pos, state);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        Box area = new Box(pos).expand(45);
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class, area,
                entity -> true
        );

        for (LivingEntity entity : entities) {
            if (!entity.getOffHandStack().isOf(HeraldItems.COVENANT)) {
                entity.addStatusEffect(new StatusEffectInstance(HeraldEffects.STRAINED, 60));
            }
        }

        world.scheduleBlockTick(pos, this, 20); // 1 second later
    }
}
