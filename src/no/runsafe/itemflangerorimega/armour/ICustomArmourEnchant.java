package no.runsafe.itemflangerorimega.armour;

import no.runsafe.framework.minecraft.event.entity.RunsafeEntityDamageByEntityEvent;
import no.runsafe.framework.minecraft.event.entity.RunsafeEntityDamageEvent;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public interface ICustomArmourEnchant
{
	public String getEnchantText();
	public String getSimpleName();
	public void entityDamageEvent(RunsafeMeta item, RunsafeEntityDamageEvent event);
	public void entityDamageByEntityEvent(RunsafeMeta item, RunsafeEntityDamageByEntityEvent event);
}
