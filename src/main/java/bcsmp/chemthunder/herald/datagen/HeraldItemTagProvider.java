package bcsmp.chemthunder.herald.datagen;

import bcsmp.chemthunder.herald.Herald;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class HeraldItemTagProvider extends FabricTagProvider<Item> {
    public HeraldItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    public static final TagKey<Item> SOLTIUDE_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Herald.MOD_ID, "solitude_items"));

    public static final TagKey<Item> CRIMSON_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Herald.MOD_ID, "crimson_items"));

    public static final TagKey<Item> NAIL_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Herald.MOD_ID, "nail_items"));

    public static final TagKey<Item> DIVINE_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Herald.MOD_ID, "divine_items"));



    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(SOLTIUDE_ITEMS)
                .add(Items.QUARTZ)
                .setReplace(true);

        getOrCreateTagBuilder(CRIMSON_ITEMS)
                .add(Items.GOLD_INGOT)
                .setReplace(true);

        getOrCreateTagBuilder(NAIL_ITEMS)
                .add(Items.BLAZE_POWDER)
                .add(Items.BLAZE_ROD)
                .setReplace(true);

        getOrCreateTagBuilder(DIVINE_ITEMS)
                .add(Items.QUARTZ)
                .setReplace(true);
    }
}
