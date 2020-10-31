package ru.DmN.AE2AO.mixin;

import appeng.api.networking.pathing.ControllerState;
import appeng.me.cache.PathGridCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.DmN.AE2AO.Main;

@Mixin(value={PathGridCache.class})
public class PathGridCacheMixin {
    @Shadow
    private ControllerState controllerState;

    @Inject(method={"onUpdateTick"}, at={@At(value="FIELD")}, remap=false)
    private void onUpdateTickFieldInject(CallbackInfo ci) {
        if (this.controllerState != ControllerState.NO_CONTROLLER && Main.moreControllersOnNetwork) {
            this.controllerState = ControllerState.CONTROLLER_ONLINE;
        }
    }
}
