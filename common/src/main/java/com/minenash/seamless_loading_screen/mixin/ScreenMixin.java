package com.minenash.seamless_loading_screen.mixin;

import com.minenash.seamless_loading_screen.ScreenshotLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Screen.class, priority = 900)
public abstract class ScreenMixin {

    @Inject(method = "renderDarkening(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    private void renderScreenBackground_AfterTexture(DrawContext context, CallbackInfo ci) {
        if (!ScreenshotLoader.shouldReplaceBackground()) return;

        ScreenshotLoader.render((Screen) (Object) this, context);

        ci.cancel();
    }

    @Inject(method = "renderPanoramaBackground", at = @At("HEAD"), cancellable = true)
    private void disablePanoramaBackground(DrawContext context, float delta, CallbackInfo ci) {
        if (!ScreenshotLoader.shouldReplaceBackground()) return;

        ci.cancel();
    }

    @Inject(method = "close", at = @At("HEAD"))
    private void stopBackgroundReplacment(CallbackInfo ci) {
        if (!(((Screen) (Object) this) instanceof DownloadingTerrainScreen)) return;

        ScreenshotLoader.endBackgroundReplacment();
    }
}
