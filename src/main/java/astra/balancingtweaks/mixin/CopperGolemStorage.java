package astra.balancingtweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.entity.ai.behavior.TransportItemsBetweenContainers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TransportItemsBetweenContainers.class)
public class CopperGolemStorage {

    @ModifyExpressionValue(method = "pickupItemFromContainer", at = @At(value = "CONSTANT", args = "intValue=16"))
    private static int IncreaseGolemStorageSize(int original) {
        return 64; //replaces the argument in the original .min function limiting the stack size it could grab to 16 with a limiter of this value
    }
}
