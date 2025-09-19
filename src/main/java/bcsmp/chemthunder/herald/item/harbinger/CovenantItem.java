package bcsmp.chemthunder.herald.item.harbinger;

import bcsmp.chemthunder.herald.index.HeraldDamageSources;
import bcsmp.chemthunder.herald.index.HeraldItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CovenantItem extends Item {
    public CovenantItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack main = user.getStackInHand(hand);

        if (user.isSneaking() && world instanceof ServerWorld serverWorld) {
            LivingEntity livingEntity = (LivingEntity) user;

            main.decrement(1);
            user.giveItemStack(HeraldItems.SIGNED_COVENANT.getDefaultStack());
            user.damage(HeraldDamageSources.contract(livingEntity), 3);
            user.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1, 0);

            serverWorld.spawnParticles(ParticleTypes.ENCHANT, user.getX(), user.getY() + 1, user.getZ(), 50, 0.5, 1, 0.5, 0.7);
        }

        return super.use(world, user, hand);
    }
}
