package net.dohaw.safestop;

import net.dohaw.corelib.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseConfig extends Config {

    public BaseConfig(JavaPlugin plugin) {
        super(plugin, "config.yml");
    }

    public long getInterval(){
        return (long) ((config.getDouble("Interval", 1)) * 20);
    }

    public long getDelay(){
        return (long) ((config.getDouble("Initial Startup Delay", 60)) * 20);
    }

    public double getCutoffTPS(){
        return config.getDouble("Cutoff TPS", 5);
    }

    public int getCutoffRam(){
        return config.getInt("Cutoff Ram", 6144);
    }

    public String getMessage(){
        return config.getString("Message", "The server is restarting in %num% seconds!");
    }

}
