package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface HeraldDataComponents {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    ComponentType<Integer> RAGNAROK_USES = create("ragnarok_uses", new ComponentType.Builder<Integer>()
            .codec(Codec.INT)
            .build());

    static <T extends ComponentType<?>> T create(String name, T component) {
        COMPONENTS.put(component, Herald.id(name));
        return component;
    }

    static void init() {
        COMPONENTS.keySet().forEach((component) -> {
            Registry.register(Registries.DATA_COMPONENT_TYPE, COMPONENTS.get(component), component);
        });
    }
}
