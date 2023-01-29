package com.jedisebas.meliusores.init;

import com.jedisebas.meliusores.MeliusOres;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MeliusOres.MOD_ID);

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
            () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(4).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));

    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL)
                    .harvestLevel(0).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(3f)));

    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<T> block) {
        final RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(final String name, final RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));
    }

    public static void register(final IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
