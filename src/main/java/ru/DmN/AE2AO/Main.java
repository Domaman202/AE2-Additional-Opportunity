package ru.DmN.AE2AO;

import appeng.tile.networking.ControllerBlockEntity;
import com.github.mouse0w0.fastreflection.FastReflection;
import com.github.mouse0w0.fastreflection.MethodAccessor;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    // Config
    public static boolean DisableChannels;
    public static boolean EnableControllerNoSingleControllers;
    public static int controllerX1 = -7;
    public static int controllerX2 = 7;
    public static int controllerY1 = -7;
    public static int controllerY2 = 7;
    public static int controllerZ1 = -7;
    public static int controllerZ2 = 7;
    // Reflection
//    public static Method getProxy = null;
    public static MethodAccessor getProxy = null;

    @Override
    public void onInitialize() {
        // Config loading
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        // Config setup
        DisableChannels = config.DisableChannels;
        EnableControllerNoSingleControllers = config.EnableControllerNoSingleControllers;
        controllerX1 = config.controllerX1;
        controllerX2 = config.controllerX2;
        controllerY1 = config.controllerY1;
        controllerY2 = config.controllerY2;
        controllerZ1 = config.controllerZ1;
        controllerZ2 = config.controllerZ2;
        // Reflection setup
        try {
            getProxy = FastReflection.create(ControllerBlockEntity.class.getMethod("getProxy"));
        } catch (Throwable e) {
            System.out.println(e);
        }
    }
}

