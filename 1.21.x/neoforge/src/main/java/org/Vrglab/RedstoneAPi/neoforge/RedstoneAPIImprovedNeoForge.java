package org.Vrglab.RedstoneAPi.neoforge;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.Vrglab.RedstoneApi.RedstoneAPIImproved;
import org.Vrglab.TestRedstoneBlock;

import java.util.function.Supplier;

@Mod(RedstoneAPIImproved.MOD_ID)
public final class RedstoneAPIImprovedNeoForge {
    static DeferredRegister.Blocks BLOCK_REGISTRY = DeferredRegister.createBlocks(RedstoneAPIImproved.MOD_ID);

    static DeferredBlock<TestRedstoneBlock> BLOCK = BLOCK_REGISTRY.register("redstone_block_test", () -> new TestRedstoneBlock(BlockBehaviour.Properties.of()));



    public RedstoneAPIImprovedNeoForge() {
        BLOCK_REGISTRY.register(ModLoadingContext.get().getActiveContainer().getEventBus());
        RedstoneAPIImproved.init();
    }
}
