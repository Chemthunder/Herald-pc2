package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface HeraldDamageSources {
    RegistryKey<DamageType> SOLITUDE_KILL = of("solitude_kill");
    RegistryKey<DamageType> CRIMSON_KILL = of("crimson_kill");
    RegistryKey<DamageType> RESONANT_KILL = of("resonant_kill");
    RegistryKey<DamageType> SOLACE_KILL = of("solace_kill");
    RegistryKey<DamageType> OATH_KILL = of("oath_kill");

    static DamageSource solitude_kill(LivingEntity entity) {
        return entity.getDamageSources().create(SOLITUDE_KILL); }

    static DamageSource crimson_kill(LivingEntity entity) {
        return entity.getDamageSources().create(CRIMSON_KILL); }

    static DamageSource resonant_kill(LivingEntity entity) {
        return entity.getDamageSources().create(RESONANT_KILL); }

    static DamageSource solace_kill(LivingEntity entity) {
        return entity.getDamageSources().create(SOLACE_KILL); }

    static DamageSource oath_kill(LivingEntity entity) {
        return entity.getDamageSources().create(OATH_KILL); }


    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Herald.id(name));
    }
}
