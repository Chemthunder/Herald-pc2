package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.item.CrimsonObituaryItem;
import bcsmp.chemthunder.herald.item.ResonantNailItem;
import bcsmp.chemthunder.herald.item.SolitudeItem;
import bcsmp.chemthunder.herald.item.StrungBladeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

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

    Item STRUNG_BLADE = create("strung_blade", new StrungBladeItem(HeraldToolMaterial.STRUNG, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SwordItem.createAttributeModifiers(HeraldToolMaterial.STRUNG, 7, -2.6f))
    ));

  //  Item MACHINE_OIL_BOTTLE = create("machine_oil_bottle", new OilItem(new Item.Settings()
   //         .maxCount(16)
  //  ));

    static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

        modifyItemNameColor(SOLITUDE, 0x1c1c21);
        modifyItemNameColor(CRIMSON_OBITUARY, 0x801b50);
        modifyItemNameColor(RESONANT_NAIL, 0x805437);
        modifyItemNameColor(STRUNG_BLADE, 0x777b8a);
    }

    private static Item create(String name, Item item) {
        ITEMS.put(item, Herald.id(name));
        return item;
    }
}
