package hlft.fabric.flamesweeping;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class FlameSweeping implements ModInitializer {
    @Override
    public void onInitialize() {

    }

    public static void damage(LivingEntity livingEntity, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity) {
            int k = EnchantmentHelper.getFireAspect((LivingEntity) source.getAttacker());
            if(livingEntity.isAlive()) {
                if (k > 0 && !livingEntity.isOnFire()) {
                    livingEntity.setOnFireFor(k * 4);
                }
            }
        }
    }
}
