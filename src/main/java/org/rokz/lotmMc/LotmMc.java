package org.rokz.lotmMc;
import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.rokz.lotmMc.Abilities.Entities.AirBulletEntity;
import org.rokz.lotmMc.Item.ModItem;
import org.rokz.lotmMc.Server.AirBulletPacket;
import org.rokz.lotmMc.Server.AirBulletPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LotmMc implements ModInitializer {
    public static final String KEYBIND_CATEGORY = "lotm_key_category";
    public static String MOD_ID = "lotmmc";
    public static Logger log = LoggerFactory.getLogger(MOD_ID);
    public static final AttachmentType<String> PlayerPath = AttachmentRegistry.create(
            Identifier.of(MOD_ID, "pathway"),
            stringBuilder -> stringBuilder
                    .persistent(Codec.STRING)
                    .syncWith(
                            PacketCodecs.STRING,  // Dictates how to turn the data into a packet to send to clients.
                            AttachmentSyncPredicate.all() // Dictates who to send the data to.
                    )
                    .copyOnDeath()
    );

    public static final RegistryKey<EntityType<?>> AIR_BULLET_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("lotmmc", "air_bullet"));

    public static final EntityType<AirBulletEntity> AIR_BULLET =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    AIR_BULLET_KEY,
                    FabricEntityTypeBuilder
                            .<AirBulletEntity>create(SpawnGroup.MISC, AirBulletEntity::new)
                            .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(10)
                            .build(AIR_BULLET_KEY)
            );

    @Override
    public void onInitialize() {
        log.info("The crimson moon shines...");
//        PayloadTypeRegistry.playS2C().register(AirBulletPayload.ID, AirBulletPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(AirBulletPayload.ID, AirBulletPayload.CODEC);
        AirBulletPacket.register();

        ModItem.registerAll();

    }

}
