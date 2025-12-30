package astra.balancingtweaks.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.ScaffoldingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingStability {

    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "intValue=7"))
    private static int ImproveStability(int original) {
        return 14; //this replaces parts of the original function that check if the distance is less than 7 with this value
    }
}
