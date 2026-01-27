package org.rokz.lotmMc;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.Identifier;
import org.rokz.lotmMc.Item.ModItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotmMc implements ModInitializer {
    public static String MOD_ID = "lotmmc";
    public static Logger log = LoggerFactory.getLogger(MOD_ID);
    public static final AttachmentType<String> PlayerPath = AttachmentRegistry.create(
            Identifier.of(MOD_ID, "pathway"),
            stringBuilder -> stringBuilder
                    .persistent(Codec.STRING)
                    .copyOnDeath()
    );

    @Override
    public void onInitialize() {
        log.info("The crimson moon shines...");
        ModItem.registerAll();

    }

}
