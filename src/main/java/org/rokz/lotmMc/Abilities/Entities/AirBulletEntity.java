package org.rokz.lotmMc.Abilities.Entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.rokz.lotmMc.LotmMc;

public class AirBulletEntity extends ProjectileEntity {

	private static final float DAMAGE = 6.0F;

	public AirBulletEntity(EntityType<? extends ProjectileEntity> type, World world) {
		super(type, world);
	}

	public AirBulletEntity(World world, LivingEntity owner) {
		super(LotmMc.AIR_BULLET, world);
		setOwner(owner);
	}

	@Override
	protected void onEntityHit(EntityHitResult hit) {
		super.onEntityHit(hit);

		Entity target = hit.getEntity();
		Entity owner = getOwner();

		assert owner != null;
		target.damage(
				(ServerWorld) owner.getEntityWorld(),
				getDamageSources().mobProjectile(this, owner instanceof LivingEntity l ? l : null),
				DAMAGE
		);

		// Knockback (air impact)
		Vec3d vel = getVelocity().normalize().multiply(0.6);
		target.addVelocity(vel.x, 0.1, vel.z);

		discard();
	}

	@Override
	protected void onBlockHit(BlockHitResult hit) {
		super.onBlockHit(hit);
		discard();
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {

	}

	@Override
	public void tick() {
		super.tick();

		// Kill after 2 seconds
		if (this.age > 40) {
			discard();
		}
	}
}
