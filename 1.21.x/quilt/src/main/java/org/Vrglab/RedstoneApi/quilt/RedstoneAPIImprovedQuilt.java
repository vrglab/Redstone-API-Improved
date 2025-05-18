package org.Vrglab.RedstoneApi.quilt;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.Vrglab.RedstoneAPI.fabriclike.RedstoneAPIImprovedFabricLike;

public final class RedstoneAPIImprovedQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Run the Fabric-like setup.
        RedstoneAPIImprovedFabricLike.init();
    }
}
