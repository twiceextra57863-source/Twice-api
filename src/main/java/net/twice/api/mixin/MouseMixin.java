package net.twice.api.mixin;

import net.minecraft.client.Mouse;
import net.twice.api.TwiceApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;

    @Inject(method = "updateMouse", at = @At("TAIL"))
    private void onUpdateMouse(CallbackInfo ci) {
        if (TwiceApi.CONFIG != null && TwiceApi.CONFIG.optimizeCamera) {
            // Placeholder for actual smoothing logic if needed, removed crude cap.
        }
    }
}
