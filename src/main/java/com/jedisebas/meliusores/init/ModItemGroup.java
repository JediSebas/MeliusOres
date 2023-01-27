package com.jedisebas.meliusores.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup MELIUS_ORES_GROUP = new ItemGroup("meliusOresTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ALEXANDRITE_GEM.get());
        }
    };
}
