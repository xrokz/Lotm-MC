package org.rokz.lotmMc.Potion;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jspecify.annotations.NonNull;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.PathwaySequence;

public class PotionItem extends Item {

	public PotionItem(Settings settings, PathwaySequence sequence) {
		super(settings);
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}


	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		ConsumableComponent consumableComponent = itemStack.get(DataComponentTypes.CONSUMABLE);
		if (consumableComponent != null) {
			return consumableComponent.consume(user, itemStack, hand);
		} else {
			return ActionResult.PASS;
		}
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (!world.isClient() && user instanceof PlayerEntity player) {

			Identifier id = Registries.ITEM.getId(stack.getItem());

			if (id.getNamespace().equals(LotmMc.MOD_ID)) {
				String[] parts = id.getPath().split("_");
				String pathway = parts[0];
				int sequence = Integer.parseInt(parts[1]);

				consumePotion(player, pathway, sequence);
			}
		}

		return super.finishUsing(stack, world, user);
	}

	private void consumePotion(@NonNull PlayerEntity player, String pathway, int sequence) {

		if(player.hasAttached(LotmMc.PlayerPath)) {
			if(pathway.equals("reseter")) {
				player.removeAttached(LotmMc.PlayerPath);
				player.sendMessage(Text.of("imagine chickening out"), true);
				return;
			}
			String  playerPath = player.getAttached(LotmMc.PlayerPath);
			assert playerPath != null;

			String[] paseq =playerPath.split("_");
			String path = paseq[0];
			int seq = Integer.parseInt(paseq[1]);

//			DEBUG WARIORS
//			player.sendMessage(Text.of(player.getAttached(LotmMc.PlayerPath)), false);
//			player.sendMessage(Text.of(path + "   " + pathway), false);
//			player.sendMessage(Text.of(seq +"   "+sequence), false);

			if(path.equals(pathway)) {
				if(seq == sequence+1) {
					player.sendMessage(Text.of("advanced yoo"), true);
					player.setAttached(LotmMc.PlayerPath, pathway+"_" + sequence);
				} else if (seq <= sequence) {
					player.sendMessage(Text.of("the only way for improvement is u.. down"), true);
				} else {
					player.sendMessage(Text.empty().append("you cant jump sequences, acquire the ").append(Registries.ITEM.get(Identifier.of(LotmMc.MOD_ID, path.toLowerCase()+"_"+ (seq - 1))).getName()).append(" first!"), true);
				}
			}
			else player.sendMessage(Text.of("you have path sucker"), true);
		} else {
			if (sequence != 9) {
				player.sendMessage(Text.empty().append("you cant jump sequences, acquire the ").append(Registries.ITEM.get(Identifier.of(LotmMc.MOD_ID, pathway.toLowerCase()+"_"+9)).getName()).append(" first!"), true);
				return;
			}
			player.setAttached(LotmMc.PlayerPath, pathway+"_" + sequence);
			player.sendMessage(Text.of("welcome to corruption"), true);
		}

		player.addStatusEffect(
				new StatusEffectInstance(
						StatusEffects.NAUSEA, 20, 10-sequence
				)
		);
	}
}
