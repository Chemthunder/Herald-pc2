package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public interface HeraldItemGroup {
    RegistryKey<ItemGroup> HERALD_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Herald.id("herald"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(HeraldItems.COVENANT))
            .displayName(Text.translatable("itemGroup.herald").styled(style -> style.withColor(0x08080a)))
            .build();


    static void init() {
        Registry.register(Registries.ITEM_GROUP, HERALD_GROUP_KEY, ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(HERALD_GROUP_KEY).register(HeraldItemGroup::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(HeraldItems.MOURNERS_OATH);
        itemGroup.add(HeraldItems.SOLACE);
        itemGroup.add(HeraldItems.COVENANT);
        itemGroup.add(HeraldBlocks.SACRED_EFFIGY);
        itemGroup.add(HeraldBlocks.FORSAKEN_EFFIGY);
        itemGroup.add(HeraldItems.RAGNAROK);
        itemGroup.add(HeraldItems.PITIED_MASK);
        itemGroup.add(HeraldItems.CURSED_MASK);
    }
}
