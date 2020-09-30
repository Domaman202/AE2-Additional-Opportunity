package ru.DmN.AE2UC.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinSetup {
    @Inject(method = "loadWorld", at = @At("HEAD"))
    private void onStartLoadWorld(CallbackInfo ci)
    {
        System.out.println("4aukaCraft: the server started loading the world");
    }
}