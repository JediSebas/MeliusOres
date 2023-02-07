package com.jedisebas.meliusores.init;

import com.jedisebas.meliusores.MeliusOres;
import com.jedisebas.meliusores.content.LavaplasmaFluid;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

    public static final ResourceLocation LAVAPLASMA_STILL_RL = new ResourceLocation("meliusores:block/lavaplasma_still");
    public static final ResourceLocation LAVAPLASMA_FLOW_RL = new ResourceLocation("meliusores:block/lavaplasma_flow");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MeliusOres.MOD_ID);

    public static final RegistryObject<FlowingFluid> FLOWING_LAVAPLASMA = FLUIDS.register("flowing_lavaplasma",
            () -> new LavaplasmaFluid.Flowing(ModFluids.LAVAPLASMA_PROPERTIES));

    public static final RegistryObject<FlowingFluid> LAVAPLASMA = FLUIDS.register("lavaplasma",
            () -> new LavaplasmaFluid.Source(ModFluids.LAVAPLASMA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LAVAPLASMA_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> LAVAPLASMA.get(), () -> FLOWING_LAVAPLASMA.get(), FluidAttributes.builder(LAVAPLASMA_STILL_RL, LAVAPLASMA_FLOW_RL))
            .block(() -> ModFluids.LAVAPLASMA_BLOCK.get());

    public static final RegistryObject<FlowingFluidBlock> LAVAPLASMA_BLOCK = ModBlocks.BLOCKS.register("lavaplasma",
            () -> new FlowingFluidBlock(() -> ModFluids.LAVAPLASMA.get(),
                    AbstractBlock.Properties.of(Material.LAVA).noCollission().randomTicks().strength(100.0F).lightLevel(blockState -> 15).noDrops()));

    public static void register(final IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
