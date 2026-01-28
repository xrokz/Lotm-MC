package org.rokz.lotmMc.Client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.LotmMcClient;
import org.rokz.lotmMc.Server.AirBulletPayload;

public class ClientListener {
	public static void initialize() {
		// Example of handling the press
		ClientTickEvents.END_CLIENT_TICK.register(client -> {

				while (LotmMcClient.ability1.wasPressed()) {
					assert client.player != null;
					client.player.sendMessage(Text.literal("Ability1"), false);
					if(client.player.hasAttached(LotmMc.PlayerPath)) {
						String path = client.player.getAttached(LotmMc.PlayerPath).split("_")[0];
						if (path.equals("hunter")) {
							AirBulletPayload payload = new AirBulletPayload(client.player.getX(), client.player.getY(), client.player.getZ());
							ClientPlayNetworking.send(payload);
							LotmMc.log.info("i sent");
						}

					}
				}
				while (LotmMcClient.ability2.wasPressed()) {
					assert client.player != null;
					client.player.sendMessage(Text.literal("Ability2"), false);
				}
				while (LotmMcClient.ability3.wasPressed()) {
					assert client.player != null;
					client.player.sendMessage(Text.literal("Ability3"), false);
				}
				while (LotmMcClient.ability4.wasPressed()) {
					assert client.player != null;
					client.player.sendMessage(Text.literal("Ability4"), false);
				}

		});

	}
}
