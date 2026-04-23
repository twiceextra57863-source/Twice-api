package net.twice.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TwiceConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "twice-api.json");

    public boolean optimizeCamera = true;
    public boolean optimizeEntities = true;
    public boolean optimizeParticles = true;
    public boolean optimizeChunks = true;
    public boolean optimizeNameplates = true;
    public int entityCullDistance = 128;
    public int maxParticles = 2000;

    public static TwiceConfig load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                return GSON.fromJson(reader, TwiceConfig.class);
            } catch (IOException e) {
                TwiceApi.LOGGER.error("Failed to load config", e);
            }
        }
        TwiceConfig config = new TwiceConfig();
        config.save();
        return config;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            TwiceApi.LOGGER.error("Failed to save config", e);
        }
    }
}
