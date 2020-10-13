package ru.DmN.AE2UC.mixin;

import appeng.api.networking.pathing.ControllerState;
import appeng.me.cache.PathGridCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.DmN.AE2UC.Main;

@Mixin(PathGridCache.class)
public class PathGridCacheMixin {
    @Shadow private ControllerState controllerState;

    @Inject(method = "onUpdateTick", at = @At("FIELD"), remap = false)
    @Unique
    private void onUpdateTickFIELD(CallbackInfo ci) {
        if (controllerState != ControllerState.NO_CONTROLLER && Main.EnableControllerNoSingleControllers)
            controllerState = ControllerState.CONTROLLER_ONLINE;
    }
}
