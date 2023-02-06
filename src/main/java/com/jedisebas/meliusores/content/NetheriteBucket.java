package com.jedisebas.meliusores.content;

import com.jedisebas.meliusores.init.ModItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class NetheriteBucket extends BucketItem {

    private final Fluid content;

    public NetheriteBucket(final Properties builder) {
        super(() -> Fluids.EMPTY, builder);
        this.content = Fluids.EMPTY;
    }

    public NetheriteBucket(final Fluid containedFluid, final Properties builder) {
        super(() -> containedFluid, builder);
        this.content = containedFluid;
    }

    @Override
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> use(final World worldIn, final PlayerEntity playerIn, final Hand handIn) {

        final ItemStack itemStack = playerIn.getItemInHand(handIn);
        final BlockRayTraceResult rayTraceResult = getPlayerPOVHitResult(worldIn, playerIn, this.content == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
        final ActionResult<ItemStack> actionResult = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStack, rayTraceResult);

        if (actionResult != null) {
            return actionResult;
        }

        if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemStack);
        } else if (rayTraceResult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.pass(itemStack);
        } else {
            final BlockPos blockPos = rayTraceResult.getBlockPos();
            final Direction direction = rayTraceResult.getDirection();
            final BlockPos blockPos1 = blockPos.relative(direction);

            if (worldIn.mayInteract(playerIn, blockPos) && playerIn.mayUseItemAt(blockPos1, direction, itemStack)) {
                final BlockState blockState = worldIn.getBlockState(blockPos);
                if (this.content == Fluids.EMPTY) {
                    if (blockState.getBlock() instanceof IBucketPickupHandler) {
                        final Fluid fluid = ((IBucketPickupHandler) blockState.getBlock()).takeLiquid(worldIn, blockPos, blockState);
                        if (fluid != Fluids.EMPTY) {
                            playerIn.awardStat(Stats.ITEM_USED.get(this));

                            SoundEvent soundEvent = this.content.getAttributes().getFillSound();
                            if (soundEvent == null) {
                                soundEvent = fluid.is(FluidTags.LAVA) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL;
                            }
                            playerIn.playSound(soundEvent, 1.0F, 1.0F);

                            final ItemStack itemStack1 = DrinkHelper.createFilledResult(itemStack, playerIn, getAccurateBucket(fluid.getBucket()));
                            if (!worldIn.isClientSide) {
                                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerIn, getAccurateBucket(fluid.getBucket()));
                            }

                            return ActionResult.sidedSuccess(itemStack1, worldIn.isClientSide());
                        }
                    }

                    return ActionResult.fail(itemStack);
                } else {
                    final BlockPos blockPos2 = canBlockContainFluid(worldIn, blockPos, blockState) ? blockPos : blockPos1;
                    if (this.emptyBucket(playerIn, worldIn, blockPos2, rayTraceResult)) {
                        this.checkExtraContent(worldIn, itemStack, blockPos2);
                        if (playerIn instanceof ServerPlayerEntity) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockPos2, itemStack);
                        }

                        final ItemStack itemStack1 = new ItemStack(ModItems.NETHERITE_BUCKET.get());
                        playerIn.awardStat(Stats.ITEM_USED.get(this));
                        if (playerIn.isCreative()) {
                            return ActionResult.sidedSuccess(itemStack, worldIn.isClientSide());
                        }
                        return ActionResult.sidedSuccess(itemStack1, worldIn.isClientSide());
                    } else {
                        return ActionResult.fail(itemStack);
                    }
                }
            } else {
                return ActionResult.fail(itemStack);
            }
        }
    }
    
    private ItemStack getAccurateBucket(final Item bucket) {
        return bucket.equals(Items.WATER_BUCKET) ? new ItemStack(ModItems.NETHERITE_BUCKET_WATER.get()) : new ItemStack(ModItems.NETHERITE_BUCKET_LAVA.get());
    }
    
    private boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof ILiquidContainer && ((ILiquidContainer)blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.content);
    }
}
