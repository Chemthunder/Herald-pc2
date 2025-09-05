package bcsmp.chemthunder.herald.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class StrungBladeItem extends SwordItem implements CustomHitParticleItem, CustomHitSoundItem {
    public StrungBladeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x999ba3, 0x6d7180), new SweepParticleEffect(0x212633, 0x161922)};

    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.BLOCK_CHERRY_WOOD_BREAK, 50, 3);
    }
}
