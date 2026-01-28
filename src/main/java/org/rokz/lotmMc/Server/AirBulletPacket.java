package org.rokz.lotmMc.Server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import org.rokz.lotmMc.Abilities.Hunter;
import org.rokz.lotmMc.LotmMc;

import static org.rokz.lotmMc.Server.AirBulletPayload.ID;

public class AirBulletPacket {

	public static void register() {
		ServerPlayNetworking.registerGlobalReceiver(
				ID,
				(payload, context) -> {

					context.server().execute(() -> {
						Hunter.airBullet(context.player());
					});
				}
		);
	}
}
	