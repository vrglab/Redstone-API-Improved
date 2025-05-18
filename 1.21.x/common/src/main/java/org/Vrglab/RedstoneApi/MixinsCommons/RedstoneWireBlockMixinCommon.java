package org.Vrglab.RedstoneApi.MixinsCommons;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.Vrglab.RedstoneApi.Api.RedstoneAPIBlockRegistry;
import org.Vrglab.RedstoneApi.Api.SetRedstoneAcceptanceDir;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class RedstoneWireBlockMixinCommon {

    public static boolean shouldConnectToReplaced(BlockState blockState, @Nullable Direction direction) {
        if (blockState.is(Blocks.REDSTONE_WIRE)) {
            return true;
        }

        if (blockState.is(Blocks.REPEATER)) {
            Direction direction2 = blockState.getValue(RepeaterBlock.FACING);
            return direction2 == direction || direction2.getOpposite() == direction;
        }

        if (blockState.is(Blocks.OBSERVER)) {
            return direction == blockState.getValue(ObserverBlock.FACING);
        }

        List<SetRedstoneAcceptanceDir> blocks = RedstoneAPIBlockRegistry.getBlocksImplementing(SetRedstoneAcceptanceDir.class);

        for (SetRedstoneAcceptanceDir block : blocks) {
            if(block instanceof Block b && blockState.is(b))
               return block.RedstoneAcceptanceDir(blockState, direction);
        }

        return blockState.isSignalSource() && direction != null;
    }

    private static boolean HorizontalInput(BlockState blockState, DirectionProperty facing, Direction direction) {
        Direction BlockFacing = blockState.getValue(facing);
        Direction right = Direction.EAST, left = Direction.WEST;
        if (BlockFacing.getAxis().isHorizontal()) {
            right = BlockFacing.getClockWise();
            left = BlockFacing.getCounterClockWise();
        }
        return direction == BlockFacing || direction == right || direction == left;
    }
}
