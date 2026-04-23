package net.twice.api.mixin;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.twice.api.TwiceApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {
    @Inject(method = "onBlockChanged", at = @At("HEAD"))
    private void onBlockChanged(BlockPos pos, net.minecraft.block.BlockState oldState, net.minecraft.block.BlockState newState, CallbackInfo ci) {
        // Optimization hook for block changes (often high during PvP/Building)
    }
}
