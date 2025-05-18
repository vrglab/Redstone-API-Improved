package org.Vrglab.RedstoneApi.fabric;

import net.fabricmc.api.ModInitializer;

import org.Vrglab.RedstoneAPI.fabriclike.RedstoneAPIImprovedFabricLike;

public final class RedstoneAPIImprovedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run the Fabric-like setup.
        RedstoneAPIImprovedFabricLike.init();
    }
}
