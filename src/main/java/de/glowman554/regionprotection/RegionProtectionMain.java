package de.glowman554.regionprotection;

import de.glowman554.regionprotection.listeners.PlayerMoveListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public final class RegionProtectionMain extends JavaPlugin {

    private final FileConfiguration config = getConfig();

    private final ArrayList<Region> regions = new ArrayList<>();

    private static RegionProtectionMain instance;

    public RegionProtectionMain() {
        instance = this;
    }


    private void loadRegions() {
        ConfigurationSection regionsSection = config.getConfigurationSection("regions");

        for (String id : regionsSection.getKeys(false)) {
            int startX = regionsSection.getInt(id + ".startX");
            int startZ = regionsSection.getInt(id + ".startZ");
            int endX = regionsSection.getInt(id + ".endX");
            int endZ = regionsSection.getInt(id + ".endZ");
            String world = regionsSection.getString(id + ".world");
            String permission = regionsSection.getString(id + ".permission");

            Region region = new Region(startX, startZ, endX, endZ, world, permission);
            getLogger().log(Level.INFO, region.toString());

            regions.add(region);
        }
    }

    @Override
    public void onLoad() {
        if (config.getConfigurationSection("regions") == null) {
            config.addDefault("regions.region1.startX", 0);
            config.addDefault("regions.region1.startZ", 0);
            config.addDefault("regions.region1.endX", 10);
            config.addDefault("regions.region1.endZ", 10);
            config.addDefault("regions.region1.world", "world");
            config.addDefault("regions.region1.permission", "teams.RED");

            config.addDefault("regions.region2.startX", 0);
            config.addDefault("regions.region2.startZ", 0);
            config.addDefault("regions.region2.endX", -10);
            config.addDefault("regions.region2.endZ", -10);
            config.addDefault("regions.region2.world", "world");
            config.addDefault("regions.region2.permission", "teams.BLUE");

            config.addDefault("regions.region3.startX", 0);
            config.addDefault("regions.region3.startZ", 0);
            config.addDefault("regions.region3.endX", 10);
            config.addDefault("regions.region3.endZ", -10);
            config.addDefault("regions.region3.world", "world");
            config.addDefault("regions.region3.permission", "teams.YELLOW");


            config.options().copyDefaults(true);
            saveConfig();
        }

        reloadConfig();

        loadRegions();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
    }

    public static RegionProtectionMain getInstance() {
        return instance;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }
}
