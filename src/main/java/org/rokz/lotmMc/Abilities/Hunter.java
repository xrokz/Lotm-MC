package org.rokz.lotmMc.Abilities;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.rokz.lotmMc.Abilities.Entities.AirBulletEntity;
import org.rokz.lotmMc.LotmMc;

public class Hunter {

	public static void airBullet(PlayerEntity client) {
		LotmMc.log.info("i executed");
		PlayerEntity player = client;
		assert player != null;
		World world = player.getEntityWorld();
//
		AirBulletEntity bullet = new AirBulletEntity(world, player);

		Vec3d look = player.getRotationVec(1.0F);

		bullet.setPosition(
				player.getX(),
				player.getEyeY() - 0.1,
				player.getZ()
		);

		bullet.setVelocity(
				look.x,
				look.y,
				look.z,
				3.5F, // speed (air bullet = fast)
				0.0F  // no inaccuracy
		);

		world.spawnEntity(bullet);
		player.sendMessage(Text.of("Air Bullet Fired"), true);
	}

}
