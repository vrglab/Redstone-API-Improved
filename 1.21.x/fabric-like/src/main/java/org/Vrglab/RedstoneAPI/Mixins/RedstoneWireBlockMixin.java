package org.Vrglab.RedstoneAPI.Mixins;


import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.Vrglab.RedstoneApi.MixinsCommons.RedstoneWireBlockMixinCommon;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RedStoneWireBlock.class)
public class RedstoneWireBlockMixin {

    @Redirect(method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z"))
    private boolean shouldConnectTo_getConnectingSide(BlockState blockState, @Nullable Direction direction) {
        return RedstoneWireBlockMixinCommon.shouldConnectToReplaced(blockState, direction);
    }

    @Redirect(method = "shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/RedStoneWireBlock;shouldConnectTo(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z"))
    private static boolean shouldConnectTo_shouldConnectTo(BlockState blockState, @Nullable Direction direction) {
        return RedstoneWireBlockMixinCommon.shouldConnectToReplaced(blockState, direction);
    }
}
