package com.jedisebas.meliusores.datagen;

import com.jedisebas.meliusores.MeliusOres;
import com.jedisebas.meliusores.init.ModBlocks;
import mod.alexndr.simplecorelib.helpers.TagUtils;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ModBlockTags extends BlockTagsProvider {
    public ModBlockTags(final DataGenerator generatorIn, @Nullable final ExistingFileHelper existingFileHelper) {
        super(generatorIn, MeliusOres.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        registerOreTags();
        registerStorageBlockTags();
    }

    private void registerOreTags() {
        this.tag(TagUtils.forgeBlockTag("ores"))
                .addTag(TagUtils.forgeBlockTag("ores/alexandrite"));

        this.tag(TagUtils.forgeBlockTag("ores/alexandrite"))
                .add(ModBlocks.ALEXANDRITE_ORE.get());
    }

    private void registerStorageBlockTags() {
        this.tag(TagUtils.forgeBlockTag("storage_blocks"))
                .addTag(TagUtils.forgeBlockTag("storage_blocks/alexandrite"));

        this.tag(TagUtils.forgeBlockTag("storage_blocks/alexandrite"))
                .add(ModBlocks.ALEXANDRITE_BLOCK.get());
    }
}
