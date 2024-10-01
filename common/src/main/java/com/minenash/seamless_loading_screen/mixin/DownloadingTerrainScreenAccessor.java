package com.minenash.seamless_loading_screen.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.function.BooleanSupplier;

@Mixin(DownloadingTerrainScreen.class)
public interface DownloadingTerrainScreenAccessor {

    @Accessor("shouldClose")
    BooleanSupplier sls$shouldClose();
}
