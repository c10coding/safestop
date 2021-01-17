package net.dohaw.safestop;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class SafeStopRunnable extends BukkitRunnable {

    private int count = 60;
    private SafeStop plugin;

    public SafeStopRunnable(SafeStop plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {

        double tps = plugin.getTps();
        double cutOffTPS = plugin.getCutoffTPS();
        int cutoffRam = plugin.getCutoffRam();
        long ramUsed = getRamUsed();

        if(tps < cutOffTPS || ramUsed > cutoffRam){
            restart();
        }

    }

    private long getRamUsed(){
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        long freeMemory = rt.freeMemory();
        return maxMemory - freeMemory;
    }

    private void restart(){

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "restart");
        }, 1200);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if(count % 10 == 0){
                String msg = plugin.getMessage().replace("%num%", String.valueOf(count));
                Bukkit.broadcastMessage(msg);
            }
            count--;
        }, 0, 20);

    }

}
