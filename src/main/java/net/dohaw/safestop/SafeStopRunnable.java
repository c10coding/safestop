package net.dohaw.safestop;

import org.bukkit.scheduler.BukkitRunnable;

public class SafeStopRunnable extends BukkitRunnable {

    private SafeStop plugin;

    public SafeStopRunnable(SafeStop plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {

        double tps = plugin.getTps();
        double cutOffTPS = plugin.getCutoffTPS();
        int cutoffRam = plugin.getCutoffRam();
        long ramUsed = getRamUsed() / 1024 / 1024;

        if(tps < cutOffTPS || ramUsed > cutoffRam){
            this.cancel();
            plugin.restart();
        }

    }

    private long getRamUsed(){
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

}
