package com.jedisebas.meliusores.init;

import mod.alexndr.simplecorelib.helpers.TagUtils;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public class ModTags {
    public static class Items {
        public static final ITag.INamedTag<Item> GEMS_ALEXANDRITE = TagUtils.forgeTag("gems/alexandrite");
        public static final ITag.INamedTag<Item> ORES_ALEXANDRITE = TagUtils.forgeTag("ores/alexandrite");
        public static final ITag.INamedTag<Item> BLOCKS_ALEXANDRITE = TagUtils.forgeTag("blocks/alexandrite");
        public static final ITag.INamedTag<Item> RODS_ALEXANDRITE = TagUtils.forgeTag("rods/alexandrite");
        public static final ITag.INamedTag<Item> RODS_IRON = TagUtils.forgeTag("rods/iron");
    }

    public static class Blocks {
        public static final ITag.INamedTag<Item> ORES_ALEXANDRITE = TagUtils.forgeTag("ores/alexandrite");
        public static final ITag.INamedTag<Item> BLOCK_ALEXANDRITE = TagUtils.forgeTag("storage_blocks/alexandrite");
    }
}
