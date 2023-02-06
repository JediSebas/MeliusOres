package com.jedisebas.meliusores.init;

import com.jedisebas.meliusores.MeliusOres;
import com.jedisebas.meliusores.content.*;
import mod.alexndr.simplecorelib.content.SimpleShearsItem;
import net.minecraft.fluid.Fluids;
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

    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod",
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
            () -> new BowTwo(new Item.Properties().durability(1000).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow",
            () -> new BowOne(new Item.Properties().durability(750).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<NetheriteBucket> NETHERITE_BUCKET = ITEMS.register("netherite_bucket",
            () -> new NetheriteBucket(new Item.Properties().stacksTo(16).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<NetheriteBucket> NETHERITE_BUCKET_WATER = ITEMS.register("netherite_bucket_water",
            () -> new NetheriteBucket(Fluids.WATER, new Item.Properties().stacksTo(1).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static final RegistryObject<NetheriteBucket> NETHERITE_BUCKET_LAVA = ITEMS.register("netherite_bucket_lava",
            () -> new NetheriteBucket(Fluids.LAVA, new Item.Properties().stacksTo(1).tab(ModItemGroup.MELIUS_ORES_GROUP)));

    public static void register(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
