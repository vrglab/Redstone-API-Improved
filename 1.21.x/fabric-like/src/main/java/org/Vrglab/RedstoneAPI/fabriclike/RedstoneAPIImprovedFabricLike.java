package org.Vrglab.RedstoneAPI.fabriclike;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.Vrglab.RedstoneApi.RedstoneAPIImproved;
import org.Vrglab.TestRedstoneBlock;


public final class RedstoneAPIImprovedFabricLike {
    public static void init() {
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(RedstoneAPIImproved.MOD_ID,"redstone_block_test"), new TestRedstoneBlock(BlockBehaviour.Properties.of()));
        // Run our common setup.
        RedstoneAPIImproved.init();
    }
}
