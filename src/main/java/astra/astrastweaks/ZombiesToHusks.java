package astra.astrastweaks;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(MinecraftServer.class)
public class ZombiesToHusks {
    @Inject(at = @At("HEAD"),method = "")
    private void
}
