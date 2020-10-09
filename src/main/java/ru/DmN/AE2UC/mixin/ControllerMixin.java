package ru.DmN.AE2UC.mixin;

import appeng.api.networking.pathing.ControllerState;
import appeng.api.networking.pathing.IPathingGrid;
import appeng.me.helpers.AENetworkProxy;
import appeng.tile.networking.ControllerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.DmN.AE2UC.Main;

@Mixin(value = {ControllerBlockEntity.class}, remap = false, priority = 2)
public abstract class ControllerMixin {

    @Redirect(method = "updateMeta", at = @At(value = "INVOKE", target = "Lappeng/api/networking/pathing/IPathingGrid;getControllerState()Lappeng/api/networking/pathing/ControllerState;"))
    @Unique
    private ControllerState updateMeta(IPathingGrid iPathingGrid) {
        if (!Main.EnableControllerNoSingleControllers) {
            try {
                AENetworkProxy proxy = (AENetworkProxy) Main.getProxy.invoke(this, null);
                return proxy.getPath().getControllerState();
            } catch (Exception e) {
                return null;
            }
        } else
            return ControllerState.CONTROLLER_ONLINE;
    }
}