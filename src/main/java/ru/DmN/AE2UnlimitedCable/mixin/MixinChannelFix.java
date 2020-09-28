package ru.DmN.AE2UnlimitedCable.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(appeng.me.GridNode.class)
public class MixinChannelFix {
    @Shadow(remap = true)
    private static final int[] CHANNEL_COUNT = { 0, 8, Integer.MAX_VALUE };
}