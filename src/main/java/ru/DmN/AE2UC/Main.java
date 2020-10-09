package ru.DmN.AE2UC;

import appeng.tile.networking.ControllerBlockEntity;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.lwjgl.system.CallbackI;
import ru.DmN.AE2UC.mixin.ControllerMixin;

import java.lang.reflect.Method;

public class Main implements ModInitializer {

    // Config
    public static boolean DisableChannels;
    public static boolean EnableControllerNoSingleControllers;

    // Reflection
    public static Method getProxy = null;

    @Override
    public void onInitialize() {
        // Config setup
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        DisableChannels = config.DisableChannels;
        EnableControllerNoSingleControllers = config.EnableControllerNoSingleControllers;

        if (!DisableChannels && EnableControllerNoSingleControllers)
            DisableChannels = true;

        // Reflection setup
        try {
            getProxy = ControllerBlockEntity.class.getMethod("getProxy");
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }
    }
}

