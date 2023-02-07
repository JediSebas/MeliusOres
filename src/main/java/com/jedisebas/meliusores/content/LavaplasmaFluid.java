package com.jedisebas.meliusores.content;

import com.jedisebas.meliusores.init.ModBlocks;
import com.jedisebas.meliusores.init.ModFluids;
import com.jedisebas.meliusores.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.Random;

public class LavaplasmaFluid extends ForgeFlowingFluid {

    protected LavaplasmaFluid(final Properties properties) {
        super(properties);
    }

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LAVAPLASMA.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.LAVAPLASMA.get();
    }

    @Override
    public Item getBucket() {
        return ModItems.NETHERITE_BUCKET_LAVAPLASMA.get();
    }

    @Override
    public boolean isSource(final FluidState fluidState) {
        return false;
    }

    @Override
    public int getAmount(final FluidState fluidState) {
        return 8;
    }

    @Override
    protected boolean canConvertToSource() {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(final IWorld world, final BlockPos blockPos, final BlockState blockState) {
        world.levelEvent(1501, blockPos, 0);
    }

    @Override
    protected int getSlopeFindDistance(final IWorldReader worldReader) {
        return worldReader.dimensionType().ultraWarm() ? 4 : 2;
    }

    @Override
    protected int getDropOff(final IWorldReader worldReader) {
        return worldReader.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    protected boolean canBeReplacedWith(final FluidState fluidState, final IBlockReader blockReader,
                                        final BlockPos blockPos, final Fluid fluid, final Direction direction) {
        return fluidState.getHeight(blockReader, blockPos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    @Override
    public int getTickDelay(IWorldReader worldReader) {
        return worldReader.dimensionType().ultraWarm() ? 10 : 30;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

    @Override
    protected BlockState createLegacyBlock(final FluidState fluidState) {
        return ModFluids.LAVAPLASMA_BLOCK.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, Integer.valueOf(getLegacyLevel(fluidState)));
    }

    @Override
    public Fluid getFluid() {
        return super.getFluid();
    }

    @Override
    public boolean isEntityInside(FluidState state, IWorldReader world, BlockPos pos, Entity entity, double yToTest, Tag<Fluid> tag, boolean testingHead) {
        return super.isEntityInside(state, world, pos, entity, yToTest, tag, testingHead);
    }

    @Nullable
    @Override
    public Boolean isAABBInsideMaterial(FluidState state, IWorldReader world, BlockPos pos, AxisAlignedBB boundingBox, Material materialIn) {
        return super.isAABBInsideMaterial(state, world, pos, boundingBox, materialIn);
    }

    @Nullable
    @Override
    public Boolean isAABBInsideLiquid(FluidState state, IWorldReader world, BlockPos pos, AxisAlignedBB boundingBox) {
        return super.isAABBInsideLiquid(state, world, pos, boundingBox);
    }

    @Override
    public float getExplosionResistance(FluidState state, IBlockReader world, BlockPos pos, Explosion explosion) {
        return super.getExplosionResistance(state, world, pos, explosion);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(World world, BlockPos blockPos, FluidState fluidState, Random random) {
        BlockPos blockpos = blockPos.above();
        if (world.getBlockState(blockpos).isAir() && !world.getBlockState(blockpos).isSolidRender(world, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = blockPos.getX() + random.nextDouble();
                double d1 = blockPos.getY() + 1.0D;
                double d2 = blockPos.getZ() + random.nextDouble();
                world.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                world.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                world.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }

    }

    public void randomTick(World world, BlockPos blockPos, FluidState fluidState, Random random) {
        if (world.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            int i = random.nextInt(3);
            if (i > 0) {
                BlockPos blockpos = blockPos;

                for(int j = 0; j < i; ++j) {
                    blockpos = blockpos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!world.isLoaded(blockpos)) {
                        return;
                    }

                    BlockState blockstate = world.getBlockState(blockpos);
                    if (blockstate.isAir()) {
                        if (this.hasFlammableNeighbours(world, blockpos)) {
                            world.setBlockAndUpdate(blockpos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(world, blockpos, blockPos, Blocks.FIRE.defaultBlockState()));
                            return;
                        }
                    } else if (blockstate.getMaterial().blocksMotion()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos blockpos1 = blockPos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!world.isLoaded(blockpos1)) {
                        return;
                    }

                    if (world.isEmptyBlock(blockpos1.above()) && this.isFlammable(world, blockpos1, Direction.UP)) {
                        world.setBlockAndUpdate(blockpos1.above(), net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(world, blockpos1.above(), blockPos, Blocks.FIRE.defaultBlockState()));
                    }
                }
            }

        }
    }

    private boolean hasFlammableNeighbours(IWorldReader worldReader, BlockPos blockPos) {
        for(Direction direction : Direction.values()) {
            if (this.isFlammable(worldReader, blockPos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    private boolean isFlammable(IWorldReader world, BlockPos pos, Direction face) {
        return pos.getY() >= 0 && pos.getY() < 256 && !world.hasChunkAt(pos) ? false : world.getBlockState(pos).isFlammable(world, pos, face);
    }

    protected void spreadTo(IWorld world, BlockPos blockPos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = world.getFluidState(blockPos);
            if (this.is(FluidTags.LAVA) && fluidstate.is(FluidTags.WATER)) {
                if (blockState.getBlock() instanceof FlowingFluidBlock) {
                    world.setBlock(blockPos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(world, blockPos, blockPos, Blocks.STONE.defaultBlockState()), 3);
                }

                world.levelEvent(1501, blockPos, 0);
                return;
            }
        }

        super.spreadTo(world, blockPos, blockState, direction, fluidState);
    }

    protected boolean isRandomlyTicking() {
        return true;
    }

    public int getSpreadDelay(World world, BlockPos blockPos, FluidState fluidState, FluidState fluidState1) {
        int i = this.getTickDelay(world);
        if (!fluidState.isEmpty() && !fluidState1.isEmpty() && !fluidState.getValue(FALLING) && !fluidState1.getValue(FALLING) && fluidState1.getHeight(world, blockPos) > fluidState.getHeight(world, blockPos) && world.getRandom().nextInt(4) != 0) {
            i *= 4;
        }

        return i;
    }

    public boolean isSame(Fluid fluid) {
        return fluid == ModFluids.LAVAPLASMA.get() || fluid == ModFluids.FLOWING_LAVAPLASMA.get();
    }

    public static class Flowing extends LavaplasmaFluid {

        public Flowing(final Properties properties) {
            super(properties);
        }

        @Override
        protected void createFluidStateDefinition(final StateContainer.Builder<Fluid, FluidState> stateBuilder) {
            super.createFluidStateDefinition(stateBuilder);
            stateBuilder.add(LEVEL);
        }

        @Override
        public int getAmount(final FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(final FluidState fluidState) {
            return false;
        }
    }

    public static class Source extends LavaplasmaFluid {

        public Source(final Properties properties) {
            super(properties);
        }

        public int getAmount(final FluidState fluidState) {
            return 8;
        }

        public boolean isSource(final FluidState fluidState) {
            return true;
        }
    }
}
