package hlft.fabric.flamesweeping;

import crystalspider.soulfired.api.FireManager;
import crystalspider.soulfired.api.enchantment.FireTypedFireAspectEnchantment;
import crystalspider.soulfired.api.type.FireTypeChanger;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class FireAspectCompatibility {
    public static boolean damage(LivingEntity livingEntity, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity attacker) {
            var list = FireManager.getFireAspects()
                    .stream()
                    .filter((enchantment) -> EnchantmentHelper.getEquipmentLevel(enchantment, attacker) > 0)
                    .toList();
            if (!list.isEmpty()) {
                FireTypedFireAspectEnchantment enchantment = list.get(0);
                int k = EnchantmentHelper.getEquipmentLevel(enchantment, attacker);

                if(livingEntity.isAlive()) {
                    if (k > 0 && !(livingEntity.isOnFire())) {
                        livingEntity.setOnFireFor(k * 4);

                        ((FireTypeChanger)livingEntity).setFireType(FireManager.ensure(enchantment.getFireType()));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
