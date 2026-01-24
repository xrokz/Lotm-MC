package org.rokz.lotmMc.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.PathwaySequence;

public class PotionModelGenerator extends FabricModelProvider {
	public PotionModelGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerator generator) {

//		Identifier sharedModel = Identifier.of(
//				"lotmmc",
//				"item/pathway_potion"
//		);

		for (PathwaySequence seq : PathwaySequence.values()) {
			generator.register(Registries.ITEM.get(Identifier.of(LotmMc.MOD_ID, seq.name().toLowerCase())), Models.GENERATED);
		}
	}
}
