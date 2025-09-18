package bcsmp.chemthunder.herald.block;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.index.HeraldEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ForsakenEffigyBlock extends Block {
    public ForsakenEffigyBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SOUL, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, 0.1, 1, 0.1, 0.2);
        }
        if (placer instanceof ServerPlayerEntity serverPlayerEntity) {
            Box area = new Box(pos).expand(5);
            List<LivingEntity> entities = world.getEntitiesByClass(
                    LivingEntity.class, area,
                    entity -> true
            );

            for (LivingEntity entity : entities) {
                if (!entity.getOffHandStack().isOf(Items.ACACIA_FENCE)) {
                    teleportToPurgatory(serverPlayerEntity);
                }
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
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
}
