package hlft.fabric.flamesweeping.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract DamageTracker getDamageTracker();

    @Inject(at = @At("HEAD"), method = "applyDamage")
    protected void applyDamage(DamageSource source, float amount, CallbackInfo ci) {
        if(source.getAttacker() instanceof LivingEntity && source.getSource() != null) {
            int k = EnchantmentHelper.getFireAspect((LivingEntity) source.getAttacker());
            DamageTracker tracker = getDamageTracker();
            if (tracker.getEntity().isAlive()) {
                if (k > 0 && !tracker.getEntity().isOnFire()) {
                    tracker.getEntity().setOnFireFor(4 * k);
                }
            }
        }
    }
}
