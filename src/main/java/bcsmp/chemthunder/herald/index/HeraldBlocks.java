package bcsmp.chemthunder.herald.index;

import bcsmp.chemthunder.herald.Herald;
import bcsmp.chemthunder.herald.block.ForsakenEffigyBlock;
import bcsmp.chemthunder.herald.block.SacredEffigyBlock;
import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

public interface HeraldBlocks {
    Block SACRED_EFFIGY = createWithItem("sacred_effigy", SacredEffigyBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)
    );

    Block FORSAKEN_EFFIGY = createWithItem("forsaken_effigy", ForsakenEffigyBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)
    );

//  Block AMARANTHINE_BLOCK = createWithItem("amaranthine_block", AmethystBlock::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
//                .mapColor(MapColor.PALE_PURPLE)
//                );

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, Herald.id(name), block);
    }

    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        HeraldItems.create(name, itemSettings -> new TranslationBlockItem(block, itemSettings), new Item.Settings().maxCount(1));
        return block;
    }

    static void init() {

    }
}
