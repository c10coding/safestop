package net.dohaw.safestop;

import lombok.Getter;
import net.dohaw.corelib.CoreLib;
import net.dohaw.corelib.JPUtils;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeStop extends JavaPlugin {

    @Getter BaseConfig bConfig;

    @Getter private long interval, delay;
    @Getter private double tps, cutoffTPS;
    @Getter private int cutoffRam;
    @Getter private String message;

    @Override
    public void onEnable() {
        CoreLib.setInstance(this);
        JPUtils.validateFiles("config.yml");
        this.bConfig = new BaseConfig(this);
        loadValues();
        getLogger().info("The bConfig values are loaded!");
        new SafeStopRunnable(this).runTaskTimer(this, delay, interval);
        startTPSChecker();
    }

    @Override
    public void onDisable() {

    }

    public void loadValues(){
        this.interval = bConfig.getInterval();
        this.delay = bConfig.getDelay();
        this.cutoffTPS = bConfig.getCutoffTPS();
        this.cutoffRam = bConfig.getCutoffRam();
        this.message = bConfig.getMessage();
    }


    private void startTPSChecker(){

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            long sec;
            long currentSec;
            int ticks;
            int delay;

            @Override
            public void run() {

                sec = (System.currentTimeMillis() / 1000);
                if (currentSec == sec) {// this code block triggers each tick
                    ticks++;
                } else {// this code block triggers each second

                    currentSec = sec;
                    tps = (tps == 0 ? ticks : ((tps + ticks) / 2));

                    if(tps > 20){
                        tps = 20;
                    }

                    ticks = 0;

                    if ((++delay % 300) == 0) {// this code block triggers each 5 minutes
                        delay = 0;
                    }
                }

            }

        }, 0, 1); // do not change the "1" value, the other one is just initial delay, I recommend 0 = start instantly.

    }

}
