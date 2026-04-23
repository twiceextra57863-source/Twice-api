package net.twice.api;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class TwiceApi implements ModInitializer {
    public static final String MOD_ID = "twice-api";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static TwiceConfig CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = TwiceConfig.load();
        LOGGER.info("Twice API Initialized - Starting Optimizations");
    }
}
