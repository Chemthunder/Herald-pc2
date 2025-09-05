package bcsmp.chemthunder.herald.item;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.index.HeraldDamageSources;
import bcsmp.chemthunder.herald.index.HeraldSoundEvents;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GraceItem extends SwordItem implements CustomHitParticleItem, CustomKillSourceItem, ShieldBreaker {
    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x1c6a91, 0x17465e), new SweepParticleEffect(0xb5e6ff, 0x83cdf2)};

    public GraceItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

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
        return HeraldDamageSources.grace_kill(livingEntity);
    }

    @Override
    public int shieldCooldown() {
        return 20;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            // Set weather: clearDuration, rainDuration, raining, thundering
            serverWorld.setWeather(0, 6000, true, true);

            //feedback to player
            user.sendMessage(Text.translatable("text.grace.change_weather").withColor(0x3b5169).formatted(Formatting.ITALIC), true);
        } else {
         //client-side feedback
            user.sendMessage(Text.translatable("text.grace.change_weather_fail").withColor(0x3b5169).formatted(Formatting.ITALIC), true);
        }

        // else
        world.playSound(user, user.getBlockPos(), HeraldSoundEvents.STORM_RING, SoundCategory.MASTER);

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
