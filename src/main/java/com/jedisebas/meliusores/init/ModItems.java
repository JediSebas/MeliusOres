package com.jedisebas.meliusores.init;

import com.jedisebas.meliusores.MeliusOres;
import com.jedisebas.meliusores.content.MeliusOresItemTier;
import mod.alexndr.simplecorelib.content.SimpleShearsItem;
import net.minecraft.item.*;
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

    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword",
            () -> new SwordItem(MeliusOresItemTier.ALEXANDRITE, 3, -2.4f,
                    new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe",
            () -> new PickaxeItem(MeliusOresItemTier.ALEXANDRITE, 1, -2.4f,
                    new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel",
            () -> new ShovelItem(MeliusOresItemTier.ALEXANDRITE, 1.5f, -2.4f,
                    new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe",
            () -> new AxeItem(MeliusOresItemTier.ALEXANDRITE, 2, -2.4f,
                    new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register("alexandrite_hoe",
            () -> new HoeItem(MeliusOresItemTier.ALEXANDRITE, -6, -2.4f,
                    new Item.Properties().tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_SHEARS = ITEMS.register("alexandrite_shears",
            () -> new SimpleShearsItem(new Item.Properties().durability(MeliusOresItemTier.ALEXANDRITE.getUses())
                    .tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> ALEXANDRITE_BOW = ITEMS.register("alexandrite_bow",
            () -> new BowItem(new Item.Properties().durability(750).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
