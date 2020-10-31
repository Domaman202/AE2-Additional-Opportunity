package ru.DmN.AE2AO;

import appeng.me.cache.PathGridCache;
import com.github.mouse0w0.fastreflection.FastReflection;
import com.github.mouse0w0.fastreflection.FieldAccessor;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static boolean DisableChannels;
    public static boolean moreControllersOnNetwork;
    public static int controllerX;
    public static int controllerY;
    public static int controllerZ;
    public static FieldAccessor controllers;

    public void onInitialize() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        ModConfig config = (ModConfig)AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        DisableChannels = config.DisableChannels;
        moreControllersOnNetwork = config.moreControllersOnNetwork;
        controllerX = config.controllerX;
        controllerY = config.controllerY;
        controllerZ = config.controllerZ;

        try {
            controllers = FastReflection.create(PathGridCache.class.getDeclaredField("controllers"));
        }
        catch (Throwable e) {
            System.out.println(e);
        }
    }

    static {
        controllerX = 0;
        controllerY = 0;
        controllerZ = 0;
        controllers = null;
    }
}