package hlft.fabric.flamesweeping.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract boolean isAlive();

    @Inject(at = @At("HEAD"), method = "applyDamage")
    protected void damage(DamageSource source, float amount, CallbackInfo ci) {
        if(source.getAttacker() instanceof LivingEntity) {
            int k = EnchantmentHelper.getFireAspect((LivingEntity) source.getAttacker());
            if(this.isAlive()) {
                if (k > 0 && !((Entity)(Object)this).isOnFire()) {
                    ((Entity)(Object)this).setOnFireFor(k * 4);
                }
            }
        }
    }
}
