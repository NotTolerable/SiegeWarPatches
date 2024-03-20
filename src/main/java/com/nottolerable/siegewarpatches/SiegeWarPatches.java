package com.nottolerable.siegewarpatches;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;
import com.gmail.goosius.siegewar.utils.SiegeWarDistanceUtil;
import org.bukkit.scheduler.BukkitRunnable;
import com.gmail.goosius.siegewar.settings.SiegeWarSettings;

public final class SiegeWarPatches extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                // Plugin startup logic
                System.out.println("[SiegeWarPatches] SiegeWarPatches by NotTolerable has loaded properly!");

                getServer().getPluginManager().registerEvents(this, this);
        }

        @Override
        public void onDisable() {
                // Plugin shutdown logic
                System.out.println("[SiegeWarPatches] SiegeWarPatches by NotTolerable has unloaded properly!");
        }

        @EventHandler // Water Bucket Block Glitch Prevention
        public void onPlayerPlace(BlockPlaceEvent event) {
                Player player = event.getPlayer();
                if (SiegeWarSettings.getWarSiegeEnabled()) {
                        if (SiegeWarDistanceUtil.isLocationInActiveSiegeZone(player.getLocation())) {
                                if (event.getBlockPlaced().isLiquid()) {
                                        player.setWalkSpeed(0); // Stun Effect
                                        player.setFlySpeed(0);

                                        new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                        player.setWalkSpeed(0.2f);
                                                        player.setFlySpeed(0.1f);
                                                }
                                        }.runTaskLater(this, 5);
                                } else {
                                        System.out.println("[SiegeWarPatches] Debug - Water bucket place checks cleared successfullye");
                        }
                }
                }
        }
}
