package ru.DmN.AE2UnlimitedCable.mixin;

import appeng.api.networking.*;
import appeng.api.util.AEPartLocation;
import appeng.api.util.IReadOnlyCollection;
import appeng.me.GridNode;
import appeng.me.pathfinding.IPathItem;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.EnumSet;

@Mixin(appeng.me.GridNode.class)
@Implements({@Interface(iface = IPathItem.class, prefix = "PI$"), @Interface(iface = IGridNode.class, prefix = "GN$")})
public class MixinGridNode {
//    @Shadow @Final private static int[] CHANNEL_COUNT = { 0, Integer.MAX_VALUE, Integer.MAX_VALUE };
//    @Shadow private int usedChannels = 1;


//    /**
//     * @author
//     */
//    @Intrinsic(displace = true)
//    public boolean PI$canSupportMoreChannels() {
//        return true;
//    }

    /**
     * @author
     */
    @Overwrite(remap = false)
    private int getUsedChannels() {
        return 1;
    }
}