package ru.DmN.AE2AO;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "ae2ao")
public class ModConfig implements ConfigData {
    public boolean DisableChannels = true;
    public boolean moreControllersOnNetwork = true;
    public int controllerX = 7;
    public int controllerY = 7;
    public int controllerZ = 7;
}
