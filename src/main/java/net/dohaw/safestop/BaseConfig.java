package net.dohaw.safestop;

import net.dohaw.corelib.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
    }

}
