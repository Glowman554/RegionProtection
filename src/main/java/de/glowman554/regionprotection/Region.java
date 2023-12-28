package de.glowman554.regionprotection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Region {
    private final int startX;
    private final int startZ;
    private final int endX;
    private final int endZ;
    private final String world;
    private final String permission;

    public Region(int startX, int startZ, int endX, int endZ, String world, String permission) {
        this.startX = startX;
        this.startZ = startZ;
        this.endX = endX;
        this.endZ = endZ;
        this.world = world;
        this.permission = permission;
    }

    private boolean checkBounds(int x, int z, World world)  {
        return x >= startX && x <= endX && z >= startZ && z <= endZ && world.getName().equals(this.world);
    }

    public boolean allowed(Player player) {
        Location location = player.getLocation();
        if (checkBounds(location.getBlockX(), location.getBlockZ(), location.getWorld())) {
            return player.hasPermission(permission);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Region{" +
                "startX=" + startX +
                ", startZ=" + startZ +
                ", endX=" + endX +
                ", endZ=" + endZ +
                ", world='" + world + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
