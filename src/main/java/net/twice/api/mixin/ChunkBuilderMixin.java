package net.twice.api.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import net.twice.api.TwiceApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public class ChunkBuilderMixin {
    // Correcting the method name if it changed in 1.21.4 or using a broader injection
    @Inject(method = "sendToTaskExecutor", at = @At("HEAD"), cancellable = true)
    private void onSendToTaskExecutor(ChunkBuilder.BuiltChunk chunk, boolean prioritized, CallbackInfo ci) {
        if (TwiceApi.CONFIG != null && TwiceApi.CONFIG.optimizeChunks) {
            // Optimization logic for chunk rebuilding
        }
    }
}
