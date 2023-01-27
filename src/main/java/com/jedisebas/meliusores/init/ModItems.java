package com.jedisebas.meliusores.init;

import com.jedisebas.meliusores.MeliusOres;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MeliusOres.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE_GEM = ITEMS.register("alexandrite_gem",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_ROD = ITEMS.register("alexandrite_rod",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_BOW = ITEMS.register("alexandrite_bow",
            () -> new BowItem(new Item.Properties().durability(750).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
