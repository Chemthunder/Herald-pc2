package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface HeraldSoundEvents {
    Map<SoundEvent, Identifier> SOUNDS = new LinkedHashMap<>();

    SoundEvent STORM_RING = create("event.storm_ring");

    private static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(Herald.id(name));
        SOUNDS.put(soundEvent, Herald.id(name));
        return soundEvent;
    }

    static void init() {
        SOUNDS.keySet().forEach(soundEvent -> {
            Registry.register(Registries.SOUND_EVENT, SOUNDS.get(soundEvent), soundEvent);
        });
    }
}
