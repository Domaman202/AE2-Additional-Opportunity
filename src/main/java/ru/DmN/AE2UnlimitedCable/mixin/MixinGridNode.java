package ru.DmN.AE2UnlimitedCable.mixin;

import org.spongepowered.asm.mixin.*;

@Mixin(appeng.me.GridNode.class)
public class MixinGridNode {
    /**
     * @author
     */
    @Overwrite(remap = false)
    private int getUsedChannels() {
        return 1;
    }
}