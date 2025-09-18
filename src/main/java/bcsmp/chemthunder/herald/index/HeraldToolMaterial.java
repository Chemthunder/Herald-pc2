package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.datagen.HeraldItemTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public interface HeraldToolMaterial {

    ToolMaterial SOLI = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 99999999, 5, 0, 0, HeraldItemTagProvider.SOLTIUDE_ITEMS);

    ToolMaterial CRIM = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 99999999, 5, 0, 0, HeraldItemTagProvider.CRIMSON_ITEMS);

    ToolMaterial RESO = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 99999999, 5, 0, 0, HeraldItemTagProvider.NAIL_ITEMS);

    ToolMaterial DIVINE_POINT_FIVE = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 999999999, 5, 0.5f, 50, HeraldItemTagProvider.DIVINE_ITEMS);

    ToolMaterial DIVINE = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 999999999, 5, 0f, 50, HeraldItemTagProvider.DIVINE_ITEMS);


    private static ToolMaterial create(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {

        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return miningSpeed;
            }

            @Override
            public float getAttackDamage() {
                return attackDamageBonus;
            }

            @Override
            public TagKey<Block> getInverseTag() {
                return incorrectBlocksForDrops;
            }

            @Override
            public int getEnchantability() {
                return enchantmentValue;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.fromTag(repairItems);
            }
        };
    }
}
