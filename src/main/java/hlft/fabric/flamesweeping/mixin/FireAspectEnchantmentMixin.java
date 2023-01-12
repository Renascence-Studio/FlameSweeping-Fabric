package hlft.fabric.flamesweeping.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class FireAspectEnchantmentMixin {
    @Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    public void isAcceptableItemMixin(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem().isDamageable()) {
            cir.setReturnValue(true);
        }
    }
}
