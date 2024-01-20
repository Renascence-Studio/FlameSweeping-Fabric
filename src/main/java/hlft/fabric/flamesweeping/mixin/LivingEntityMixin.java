package hlft.fabric.flamesweeping.mixin;

import hlft.fabric.flamesweeping.FireAspectCompatibility;
import hlft.fabric.flamesweeping.FlameSweeping;
import net.fabricmc.loader.api.FabricLoader;
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
        boolean flag = true;
        if(FabricLoader.getInstance().isModLoaded("soulfired")) {
            flag = !FireAspectCompatibility.damage((LivingEntity)(Object)this, source);
        }
        if (flag) {
            FlameSweeping.damage((LivingEntity)(Object)this, source);
        }
    }
}
