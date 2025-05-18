package org.Vrglab.RedstoneApi.Api;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.NotNull;

public abstract class RedstoneLogicBlock extends DiodeBlock implements SetRedstoneAcceptanceDir {

    public RedstoneLogicBlock(Properties properties) {
        super(properties);
        RedstoneAPIBlockRegistry.register(this);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.block();
    }

    @Override
    protected void checkTickOnNeighbor(Level world, BlockPos pos, BlockState state) {
        if (!world.getBlockTicks().willTickThisTick(pos, this)) {
            BlockState state_new = PowerStateHandling(state, world, pos);
            if (state_new != state) {
                world.scheduleTick(pos, this, this.getDelay(state), TickPriority.HIGH);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource randomSource) {
        BlockState state_new = PowerStateHandling(state, world, pos);
        if (state_new != state) {
            world.setBlock(pos, state_new, 3);
        }
    }

    protected int getInputSignal(Level world, BlockPos pos, Direction direction) {
        BlockPos inputPos = pos.relative(direction);
        return world.getSignal(inputPos, direction);
    }


    protected  abstract @NotNull MapCodec<? extends DiodeBlock> codec();

    protected abstract int Output(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, Direction direction);
    protected abstract BlockState PowerStateHandling(BlockState state, Level world, BlockPos pos);
    protected abstract void onRedstoneTick();
}
