package ru.DmN.AE2UC;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "ae2uc")
public class ModConfig implements ConfigData {
    public boolean DisableChannels = true;
    public boolean EnableControllerNoSingleControllers = true;

    public int controllerX1 = -7;
    public int controllerX2 = 7;
    public int controllerY1 = -7;
    public int controllerY2 = 7;
    public int controllerZ1 = -7;
    public int controllerZ2 = 7;
}