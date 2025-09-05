package bcsmp.chemthunder.herald.item;

import bcsmp.chemthunder.herald.index.HeraldDamageSources;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ResonantNailItem extends SwordItem implements CustomHitParticleItem, CustomKillSourceItem {
    public ResonantNailItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xffd561, 0x99813d), new SweepParticleEffect(0x855534, 0x613c23)};

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
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return HeraldDamageSources.resonant_kill(livingEntity);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity user) {
        user.setVelocity(user.getVelocity().x, 2, user.getVelocity().z);
        user.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 50, 2);

        return super.postHit(stack, target, user);
    }
}
