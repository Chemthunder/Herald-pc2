package bcsmp.chemthunder.herald.item.harbinger;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.index.HeraldDamageSources;
import bcsmp.chemthunder.herald.index.HeraldSoundEvents;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SolaceItem extends SwordItem implements CustomHitParticleItem, CustomKillSourceItem, CustomHitSoundItem, KillEffectItem {
    public SolaceItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x765e2e, 0x361609), new SweepParticleEffect(0x757575, 0x484848)};

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
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.ENTITY_WITHER_HURT);
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return HeraldDamageSources.solace_kill(livingEntity);
    }

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        if (victim instanceof PlayerEntity player) {
            world.playSound(player, victim.getBlockPos(), HeraldSoundEvents.STORM_RING, SoundCategory.MASTER);
            if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                teleportToPurgatory(serverPlayerEntity);
                serverPlayerEntity.setHealth(20f);
                ((ServerPlayerEntity) user).requestRespawn();
            }

        }
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, victim.getX(), victim.getY(), victim.getZ(), 75, 0.1, 4, 0.1, 0.02);
        }


    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    private void teleportToPurgatory(ServerPlayerEntity player) {
        // Correct way to create a registry key in 1.21.1
        RegistryKey<World> purgatoryWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of("herald", "purgatory"));

        // Get the target dimension
        MinecraftServer server = player.getServer();
        if (server == null) {
            Herald.LOGGER.error("Server is null!");
            return;
        }

        ServerWorld targetWorld = server.getWorld(purgatoryWorldKey);
        if (targetWorld != null) {
            // Get target spawn position
            BlockPos spawnPos = targetWorld.getSpawnPos();

            // Teleport the player
            player.teleport(
                    targetWorld,
                    spawnPos.getX() + 0.5,
                    spawnPos.getY(),
                    spawnPos.getZ() + 0.5,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            Herald.LOGGER.error("Could not find purgatory dimension!");
        }
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) {
        Entity owner = entity.getOwner();
        World ownerWorld = owner.getWorld();

        BlockPos pos = entity.getBlockPos();

        if (owner instanceof PlayerEntity player && ownerWorld instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.ENCHANT, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, 1, 1, 1, 0.5);
        }

        super.onItemEntityDestroyed(entity);
    }
}
