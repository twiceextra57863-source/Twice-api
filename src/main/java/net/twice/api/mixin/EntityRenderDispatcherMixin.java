package net.twice.api.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.twice.api.TwiceApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void onShouldRender(E entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (TwiceApi.CONFIG != null && TwiceApi.CONFIG.optimizeEntities) {
            if (!(entity instanceof PlayerEntity) && !(entity instanceof ProjectileEntity)) {
                double distSq = x*x + y*y + z*z;
                double cullDist = TwiceApi.CONFIG.entityCullDistance;
                if (distSq > (cullDist * cullDist)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
