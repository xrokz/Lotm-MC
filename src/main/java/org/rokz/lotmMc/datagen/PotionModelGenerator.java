package org.rokz.lotmMc.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.NonNull;
import org.rokz.lotmMc.LotmMc;
import org.rokz.lotmMc.PathwaySequence;

public class PotionModelGenerator extends FabricModelProvider {
	public PotionModelGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(@NonNull BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(@NonNull ItemModelGenerator generator) {
		for (PathwaySequence seq : PathwaySequence.values()) {
			Item item = Registries.ITEM.get(Identifier.of(LotmMc.MOD_ID, seq.name().toLowerCase()));
			final Identifier modelId = Models.GENERATED.upload(item, TextureMap.layer0(Identifier.of(LotmMc.MOD_ID, "item/"+seq.name().toLowerCase().split("_")[0])), generator.modelCollector);
			generator.output.accept(item, ItemModels.basic(modelId));
		}
	}
}
