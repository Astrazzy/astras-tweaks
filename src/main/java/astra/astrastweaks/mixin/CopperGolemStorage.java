package astra.astrastweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.ai.behavior.TransportItemsBetweenContainers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TransportItemsBetweenContainers.class)
public class CopperGolemStorage {

    @ModifyExpressionValue(method = "pickupItemFromContainer", at = @At(value = "CONSTANT", args = "intValue=16"))
    private static int IncreaseGolemStorageSize(int original) {
        return 64;
    }
}
