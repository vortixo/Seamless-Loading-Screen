package com.minenash.seamless_loading_screen.mixin;

import com.minenash.seamless_loading_screen.OnLeaveHelper;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class ClientCommonNetworkHandlerMixin {
    @Shadow public abstract void onDisconnected(DisconnectionInfo info);

    @Unique
    private boolean seamless_loading_screen$haltDisconnect = true;

    @Inject(method = "onDisconnected", at = @At("HEAD"), cancellable = true)
    private void onServerOrderedDisconnect(DisconnectionInfo info, CallbackInfo ci) {
        if (seamless_loading_screen$haltDisconnect) {
            OnLeaveHelper.beginScreenshotTask(() -> this.onDisconnected(info));

            ci.cancel();
        }

        seamless_loading_screen$haltDisconnect = !seamless_loading_screen$haltDisconnect;
    }
}
