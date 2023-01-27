package com.jedisebas.meliusores.datagen;

import com.jedisebas.meliusores.MeliusOres;
import com.jedisebas.meliusores.init.ModItems;
import mod.alexndr.simplecorelib.helpers.TagUtils;
import mod.alexndr.simpleores.datagen.ModBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ModItemTags extends ItemTagsProvider {
    public ModItemTags(final DataGenerator generatorIn, @Nullable final ExistingFileHelper existingFileHelper) {
        super(generatorIn, new ModBlockTags(generatorIn, existingFileHelper), MeliusOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        registerGemsTags();
        registerRodsTags();
    }

    private void registerGemsTags() {
        this.tag(TagUtils.forgeTag("gems"))
                .addTag(TagUtils.forgeTag("gems/alexandrite"));

        this.tag(TagUtils.forgeTag("gems/alexandrite"))
                .add(ModItems.ALEXANDRITE_GEM.get());
    }

    private void registerRodsTags() {
        this.tag(TagUtils.forgeTag("rods"))
                .addTag(TagUtils.forgeTag("rods/alexandrite"));

        this.tag(TagUtils.forgeTag("rods/alexandrite"))
                .add(ModItems.ALEXANDRITE_ROD.get());
    }
}
