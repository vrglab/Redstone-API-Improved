package org.Vrglab.RedstoneApi.Api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedstoneAPIBlockRegistry {
    private static final List<Block> REGISTERED_BLOCKS = new ArrayList<>();

    public static void register(Block block) {
        REGISTERED_BLOCKS.add(block);
    }

    public static List<Block> getAllBlocks() {
        return Collections.unmodifiableList(REGISTERED_BLOCKS);
    }

    public static <T> List<T> getBlocksImplementing(Class<T> interfaceClass) {
        List<T> result = new ArrayList<>();
        for (Block block : REGISTERED_BLOCKS) {
            if (interfaceClass.isAssignableFrom(block.getClass())) {
                result.add(interfaceClass.cast(block));
            }
        }
        return result;
    }
}
