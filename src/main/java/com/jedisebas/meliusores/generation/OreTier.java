package com.jedisebas.meliusores.generation;

import com.jedisebas.meliusores.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreTier {

    ALEXANDRITE(Lazy.of(ModBlocks.ALEXANDRITE_ORE), 4, 5, 30);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    OreTier(final Lazy<Block> block, final int maxVeinSize, final int minHeight, final int maxHeight) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreTier get(final Block block) {
        for (OreTier ore: values()) {
            if (block.equals(ore.block)) {
                return ore;
            }
        }
        return null;
    }
}
