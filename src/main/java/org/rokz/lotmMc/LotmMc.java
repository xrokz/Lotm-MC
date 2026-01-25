package org.rokz.lotmMc;
import net.fabricmc.api.ModInitializer;

import org.rokz.lotmMc.Item.ModItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotmMc implements ModInitializer {
    public static String MOD_ID = "lotmmc";
    public static Logger log = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        log.info("The crimson moon shines...");
        ModItem.registerAll();

    }

}
