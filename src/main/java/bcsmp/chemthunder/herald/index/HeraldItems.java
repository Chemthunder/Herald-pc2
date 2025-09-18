package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.item.CrimsonObituaryItem;
import bcsmp.chemthunder.herald.item.ResonantNailItem;
import bcsmp.chemthunder.herald.item.SolitudeItem;
import bcsmp.chemthunder.herald.item.harbinger.CovenantItem;
import bcsmp.chemthunder.herald.item.harbinger.FatedChainsItem;
import bcsmp.chemthunder.herald.item.harbinger.MournersOathItem;
import bcsmp.chemthunder.herald.item.harbinger.RagnarokItem;
import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface HeraldItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    Item SOLITUDE = create("solitude", new SolitudeItem(HeraldToolMaterial.SOLI, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SwordItem.createAttributeModifiers(HeraldToolMaterial.SOLI, 8, -2.7f))
    ));

    Item CRIMSON_OBITUARY = create("crimson_obituary", new CrimsonObituaryItem(HeraldToolMaterial.CRIM, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SwordItem.createAttributeModifiers(HeraldToolMaterial.CRIM, 6, -2.5f))
    ));

    Item RESONANT_NAIL = create("resonant_nail", new ResonantNailItem(HeraldToolMaterial.RESO, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SwordItem.createAttributeModifiers(HeraldToolMaterial.RESO, 7, -2.3f))
    ));

    Item MOURNERS_OATH = create("mourners_oath", new MournersOathItem(new AcornItemSettings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    Item COVENANT = create("covenant", new CovenantItem(new AcornItemSettings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    Item RAGNAROK = create("ragnarok", new RagnarokItem(new AcornItemSettings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    Item FATED_CHAINS = create("fated_chains", new FatedChainsItem(new AcornItemSettings()
            .maxCount(1)
            .rarity(Rarity.UNCOMMON)
    ));

    Item GRAVE_REMEMBRANCE = create("grave_remembrance", new Item(new AcornItemSettings()
            .maxCount(1)
    ));

    //  Item MACHINE_OIL_BOTTLE = create("machine_oil_bottle", new OilItem(new Item.Settings()
    //         .maxCount(16)
    //  ));

    static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

        modifyItemNameColor(SOLITUDE, 0x1c1c21);
        modifyItemNameColor(CRIMSON_OBITUARY, 0x801b50);
        modifyItemNameColor(RESONANT_NAIL, 0x805437);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(itemGroup -> {
            itemGroup.addAfter(Items.NETHERITE_SWORD, SOLITUDE);
            itemGroup.addAfter(Items.NETHERITE_SWORD, CRIMSON_OBITUARY);
            itemGroup.addAfter(Items.NETHERITE_SWORD, RESONANT_NAIL);
        });
    }
   private static Item create(String name, Item item) {
        ITEMS.put(item, Herald.id(name));
        return item;
    }

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, Herald.id(name), item);
    }
}
