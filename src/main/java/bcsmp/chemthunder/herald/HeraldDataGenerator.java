package bcsmp.chemthunder.herald;

import bcsmp.chemthunder.herald.datagen.HeraldItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class HeraldDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(HeraldItemTagProvider::new);
	}
}
