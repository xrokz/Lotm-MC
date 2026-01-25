package org.rokz.lotmMc.Potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jspecify.annotations.NonNull;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.PathwaySequence;

public class PotionItem extends Item {

	private final PathwaySequence sequence;

	public PotionItem(Settings settings, PathwaySequence sequence) {
		super(settings);
		this.sequence = sequence;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}


	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (!world.isClient() && user instanceof PlayerEntity player) {

			Identifier id = Registries.ITEM.getId(stack.getItem());

			if (id.getNamespace().equals(LotmMc.MOD_ID)) {
				String[] parts = id.getPath().split("_");
				String pathway = parts[0];
				int sequence = Integer.parseInt(parts[1]);

				consumePotion(world, player, pathway, sequence);
			}
		}

		return super.finishUsing(stack, world, user);
	}

	private void consumePotion(World world, @NonNull PlayerEntity player, String pathway, int sequence) {
		player.sendMessage(Text.of(pathway + sequence), false);
		player.sendMessage(Text.of(String.valueOf(this.sequence)), false);
		player.addStatusEffect(
				new StatusEffectInstance(
						StatusEffects.NAUSEA, 300, 10-sequence
				)
		);
	}
}
