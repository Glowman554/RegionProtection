package de.glowman554.regionprotection.listeners;

import de.glowman554.regionprotection.Region;
import de.glowman554.regionprotection.RegionProtectionMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        for (Region region : RegionProtectionMain.getInstance().getRegions()) {
            if (!region.allowed(player)) {
                player.sendMessage("§c§lᴅᴜ ʜᴀsᴛ ᴀᴜғ ᴅɪᴇsᴇs ɢᴇʙɪᴇᴛ ᴋᴇɪɴᴇɴ ᴢᴜᴛʀɪᴛᴛ.");
                player.performCommand("tspawn");
            }
        }
    }
}
