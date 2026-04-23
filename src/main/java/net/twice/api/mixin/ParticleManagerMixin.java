package net.twice.api.mixin;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.twice.api.TwiceApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Queue;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
    @Shadow private Map<net.minecraft.client.particle.ParticleTextureSheet, Queue<Particle>> particles;

    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", at = @At("HEAD"), cancellable = true)
    private void onAddParticle(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<Particle> cir) {
        if (TwiceApi.CONFIG != null && TwiceApi.CONFIG.optimizeParticles) {
            int total = 0;
            for (Queue<Particle> queue : particles.values()) {
                total += queue.size();
            }
            if (total > 1000) { // Hard limit on active particles
                cir.setReturnValue(null);
            }
        }
    }
}
