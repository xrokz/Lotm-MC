package org.rokz.lotmMc.Server;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.rokz.lotmMc.LotmMc;

public record AirBulletPayload(double x, double y, double z)
			implements CustomPayload {

		public static final Identifier AIRBULLET_PAYLOAD_ID =
				Identifier.of(LotmMc.MOD_ID, "air_bullet");
	public static final CustomPayload.Id<AirBulletPayload> ID = new CustomPayload.Id<>(AIRBULLET_PAYLOAD_ID);
	public static final PacketCodec<RegistryByteBuf, AirBulletPayload> CODEC =
				PacketCodec.tuple(
						PacketCodecs.DOUBLE, AirBulletPayload::x,
						PacketCodecs.DOUBLE, AirBulletPayload::y,
						PacketCodecs.DOUBLE, AirBulletPayload::z,
						AirBulletPayload::new
				);

		@Override
		public Id<? extends CustomPayload> getId() {
			return ID;
		}
	}
