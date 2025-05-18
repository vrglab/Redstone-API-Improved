package org.Vrglab.RedstoneApi.Api;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public interface SetRedstoneAcceptanceDir {

    boolean RedstoneAcceptanceDir(BlockState state, Direction dir);
}
