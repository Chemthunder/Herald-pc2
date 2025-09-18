package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.effect.StrainedEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public interface HeraldEffects {
    RegistryEntry<StatusEffect> STRAINED = create("strained", new StrainedEffect(StatusEffectCategory.NEUTRAL, 0x0000));


    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Herald.id(name), statusEffect);
    }

    static void init() {
    }
}
