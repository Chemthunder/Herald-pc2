package bcsmp.chemthunder.herald.item.harbinger;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

public class CursedMaskItem extends ArmorItem {
    public CursedMaskItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
