package no.runsafe.itemflangerorimega.tools.enchants;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;
import no.runsafe.itemflangerorimega.tools.CustomToolEnchant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SaplingGarner extends CustomToolEnchant
{
	@Override
	public String getEnchantText()
	{
		return "Sapling Garner I";
	}

	@Override
	public String getSimpleName()
	{
		return "sapling_garner";
	}

	@Override
	public void onBlockBreak(IBlock block)
	{
		Item blockMat = block.getMaterial();
		if (saplingMap.containsKey(blockMat))
		{
			RunsafeMeta dropItem = saplingMap.get(blockMat).getItem();
			dropItem.setAmount(1);
			block.getWorld().dropItem(block.getLocation(), dropItem);
		}
	}

	private final Random random = new Random();
	private static final Map<Item, Item> saplingMap;

	static
	{
		Map<Item, Item> map = new HashMap<Item, Item>();
		map.put(Item.Decoration.Leaves.Oak, Item.Decoration.Sapling.Oak);
		map.put(Item.Decoration.Leaves.Spruce, Item.Decoration.Sapling.Spruce);
		map.put(Item.Decoration.Leaves.Jungle, Item.Decoration.Sapling.Jungle);
		map.put(Item.Decoration.Leaves.DarkOak, Item.Decoration.Sapling.DarkOak);
		map.put(Item.Decoration.Leaves.Birch, Item.Decoration.Sapling.Birch);
		map.put(Item.Decoration.Leaves.Acacia, Item.Decoration.Sapling.Acacia);
		saplingMap = Collections.unmodifiableMap(map);
	}
}
