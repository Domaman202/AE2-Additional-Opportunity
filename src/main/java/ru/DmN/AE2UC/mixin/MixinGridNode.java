package ru.DmN.AE2UC.mixin;

import appeng.me.GridNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import ru.DmN.AE2UC.Main;

;

@Mixin(GridNode.class)
public abstract class MixinGridNode {
    @Shadow private int usedChannels;

    /**
     * @author
     */
    @Overwrite(remap = false)
    private int getUsedChannels() {
        return Main.DisableChannels ? 1 : this.usedChannels;
    }
}