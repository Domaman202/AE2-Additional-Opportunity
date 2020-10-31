package ru.DmN.AE2AO.mixin;

import appeng.api.networking.IGridNode;
import appeng.api.networking.pathing.ControllerState;
import appeng.api.networking.pathing.IPathingGrid;
import appeng.api.util.AEPartLocation;
import appeng.api.util.DimensionalCoord;
import appeng.me.helpers.AENetworkProxy;
import appeng.me.helpers.IGridProxyable;
import appeng.me.pathfinding.ControllerValidator;
import appeng.tile.grid.AENetworkPowerBlockEntity;
import appeng.tile.networking.ControllerBlockEntity;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.DmN.AE2AO.Main;

import java.util.Set;

@Mixin(value={ControllerBlockEntity.class}, remap=false)
@Implements(@Interface(iface = IGridProxyable.class, prefix = "proxy$"))
public abstract class ControllerBlockEntityMixin {
    @Redirect(method={"updateMeta"}, at=@At(value="INVOKE", target="Lappeng/api/networking/pathing/IPathingGrid;getControllerState()Lappeng/api/networking/pathing/ControllerState;"))
    private ControllerState updateMetaInject(IPathingGrid iPathingGrid) throws Throwable {
        if (!Main.moreControllersOnNetwork) {
//            return ((AENetworkProxy)Main.getProxy.invoke(this, new Object[0])).getPath().getControllerState();
            return ((IGridProxyable) this).getProxy().getPath().getControllerState();
        }

        AENetworkPowerBlockEntity o = (AENetworkPowerBlockEntity) ((Set)Main.controllers.get(iPathingGrid)).iterator().next();
        IGridNode startingNode = o.getGridNode(AEPartLocation.INTERNAL);

        if (startingNode == null) {
            return ControllerState.CONTROLLER_ONLINE;
        }

        DimensionalCoord dc = startingNode.getGridBlock().getLocation();
        ControllerValidator cv = new ControllerValidator(dc.x, dc.y, dc.z);
        startingNode.beginVisit(cv);
        return cv.isValid() ? ControllerState.CONTROLLER_ONLINE : ControllerState.CONTROLLER_CONFLICT;
    }
}
