package no.runsafe.itemflangerorimega.bows.enchants;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.block.IChest;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.entity.ILivingEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.entity.RunsafeItem;
import no.runsafe.framework.minecraft.entity.RunsafeLivingEntity;
import no.runsafe.framework.minecraft.entity.RunsafeProjectile;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;
import no.runsafe.itemflangerorimega.bows.CustomBowEnchant;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DispenseItem extends CustomBowEnchant
{
	@Override
	public String getEnchantText()
	{
		return "Dispense Item I";
	}

	@Override
	public String getSimpleName()
	{
		return "dispense";
	}

	@Override
	public boolean onArrowShoot(ILivingEntity entity, IEntity arrow)
	{
		int entityID = entity.getEntityId();
		if (locations.containsKey(entityID))
		{
			ILocation chestLocation = locations.get(entityID);
			IBlock chestBlock = chestLocation.getBlock();

			if (chestBlock.is(Item.Decoration.Chest))
			{
				IChest chest = (IChest) chestBlock;
				RunsafeInventory chestInventory = chest.getInventory();

				List<RunsafeMeta> items = chestInventory.getContents();

				if (!items.isEmpty())
				{
					RunsafeMeta item = items.get(0);
					chestInventory.remove(item);

					IWorld world = arrow.getWorld();
					if (world != null)
					{
						RunsafeItem itemEntity = world.dropItem(arrow.getLocation(), item);
						arrow.setPassenger(itemEntity);
					}
				}
			}
		}
		return true;
	}

	@Override
	public void onArrowCollide(RunsafeProjectile projectile)
	{
		RunsafeLivingEntity shooter = projectile.getShooter();
		if (shooter != null)
		{
			ILocation shooterLocation = shooter.getLocation();

			if (shooterLocation != null)
			{
				ILocation location = shooterLocation.clone();
				location.offset(0, -1, 0);

				IBlock block = location.getBlock();
				if (block.is(Item.Decoration.Chest))
					locations.put(shooter.getEntityId(), location);
			}
		}
	}

	private void chestCheck()
	{

	}

	private ConcurrentHashMap<Integer, ILocation> locations = new ConcurrentHashMap<Integer, ILocation>(0);
}
