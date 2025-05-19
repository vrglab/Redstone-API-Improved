package org.Vrglab;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.Vrglab.RedstoneApi.Api.RedstoneLogicBlock;
import org.jetbrains.annotations.NotNull;

public class TestRedstoneBlock extends RedstoneLogicBlock {

    public TestRedstoneBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(getStateDefinition().any()
                .setValue(LEFT_INPUT, false)
                .setValue(RIGHT_INPUT, false)
                .setValue(POWERED, true)
                .setValue(FACING, Direction.SOUTH)
        );
    }

    public static final MapCodec<TestRedstoneBlock> CODEC = simpleCodec(TestRedstoneBlock::new);

    public static final BooleanProperty LEFT_INPUT = BlockStateProperties.EAST;
    public static final BooleanProperty RIGHT_INPUT = BlockStateProperties.WEST;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LEFT_INPUT, RIGHT_INPUT, POWERED, FACING});
    }

    @Override
    public boolean RedstoneAcceptanceDir(BlockState state, Direction dir) {
        Direction BlockFacing = state.getValue(FACING);
        Direction right = Direction.EAST, left = Direction.WEST;
        if (BlockFacing.getAxis().isHorizontal()) {
            right = BlockFacing.getClockWise();
            left = BlockFacing.getCounterClockWise();
        }
        return dir == BlockFacing || dir == right || dir == left;
    }

    @Override
    protected @NotNull MapCodec<? extends DiodeBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getDelay(BlockState blockState) {
        return 1;
    }

    @Override
    protected BlockState PowerStateHandling(BlockState state_new, Level world, BlockPos pos) {
        Direction facing = state_new.getValue(FACING);
        Direction right, left;
        if (facing.getAxis().isHorizontal()) {
            right = facing.getClockWise();
            left = facing.getCounterClockWise();
        } else {
            right = Direction.EAST;
            left = Direction.WEST;
        }

        // Deal with inputs from the right of the block
        int inputSignal = getInputSignal(world, pos, right);
        if (inputSignal > 0 && !state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, true);
        }
        if (inputSignal <= 0 && state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(RIGHT_INPUT, false);
        }

        // Deal with inputs from the left of the block
        inputSignal = getInputSignal(world, pos, left);
        if (inputSignal > 0 && !state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, true);
        }
        if (inputSignal <= 0 && state_new.getValue(LEFT_INPUT)) {
            state_new = state_new.setValue(LEFT_INPUT, false);
        }

        // Deal with the output state
        if (state_new.getValue(POWERED) && state_new.getValue(LEFT_INPUT) && state_new.getValue(RIGHT_INPUT)) {
            state_new = state_new.setValue(POWERED, false);
        }
        if ((!state_new.getValue(POWERED) && !state_new.getValue(LEFT_INPUT)) || (!state_new.getValue(POWERED) && !state_new.getValue(RIGHT_INPUT))) {
            state_new = state_new.setValue(POWERED, true);
        }
        return state_new;
    }

    @Override
    protected void onRedstoneTick() {

    }
}
