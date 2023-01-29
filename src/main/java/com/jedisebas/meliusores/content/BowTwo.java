package com.jedisebas.meliusores.content;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowTwo extends BowItem {

    public BowTwo(final Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn,
                                final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("tips.damage_tooltip")).withStyle(TextFormatting.GREEN));
        tooltip.add((new TranslationTextComponent("tips.punch_tooltip")).withStyle(TextFormatting.GREEN));
        tooltip.add((new TranslationTextComponent("tips.unbreaking_tooltip")).withStyle(TextFormatting.GREEN));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void releaseUsing(ItemStack stack, final World worldIn, final LivingEntity entityLiving, final int timeLeft) {
        final Map<Enchantment, Integer> oldEnchants = EnchantmentHelper.getEnchantments(stack);
        stack = this.addBowOneEnchantments(oldEnchants, stack);

        super.releaseUsing(stack, worldIn, entityLiving, timeLeft);

        EnchantmentHelper.setEnchantments(oldEnchants, stack);
    }

    private ItemStack addBowOneEnchantments(final Map<Enchantment,Integer> oldEnchants, final ItemStack stack) {
        if (stack.isEmpty()) {
            return stack;
        }

        final Map<Enchantment,Integer> enchantmentMap = new HashMap<>(oldEnchants);
        enchantmentMap.putIfAbsent(Enchantments.POWER_ARROWS, 3);
        enchantmentMap.putIfAbsent(Enchantments.PUNCH_ARROWS, 1);
        enchantmentMap.putIfAbsent(Enchantments.UNBREAKING, 1);

        if (enchantmentMap.size() > 0) {
            EnchantmentHelper.setEnchantments(enchantmentMap, stack);
        }
        return stack;
    }
}
