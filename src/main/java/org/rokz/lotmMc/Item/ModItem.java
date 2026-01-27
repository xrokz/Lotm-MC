package org.rokz.lotmMc.Item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.rokz.lotmMc.Food.ModFood;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.Potion.PotionItem;
import org.rokz.lotmMc.PathwaySequence;

//import java.util.EnumMap;
//import java.util.Map;

public class ModItem {
//	public static final Map<PathwaySequence, Item> PATHWAY_ITEMS =
//			new EnumMap<>(PathwaySequence.class);
	public static void registerAll() {
		for (PathwaySequence pathway : PathwaySequence.values()) {
			PotionItem item = registerPathwayItem(pathway);
					ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> entries.add(item));
		}
	}

	public static PotionItem registerPathwayItem(PathwaySequence sequence) {

		Identifier id = Identifier.of(
				LotmMc.MOD_ID,
				sequence.name().toLowerCase()
		);

		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

		PotionItem.Settings settings = new PotionItem.Settings()
				.registryKey(key)
				.maxCount(1)
				.food(ModFood.PATHWAY_POTION, ConsumableComponents.drink().build()).useRemainder(Items.GLASS_BOTTLE);

		PotionItem item = new PotionItem(settings, sequence);
		return Registry.register(Registries.ITEM, id, item);
	}
}