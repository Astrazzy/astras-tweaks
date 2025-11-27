package astra.astrastweaks.mixin;

import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.tags.BiomeTags.*;

@Mixin(Zombie.class)
public abstract class ZombiesToHusk extends Entity {

    @Shadow
    protected abstract void convertToZombieType(EntityType<? extends Zombie> entityType);

    @Unique
    private EntityType<? extends Zombie> Husk;

    public ZombiesToHusk(EntityType<?> entityType, Level level, EntityType<? extends Zombie> husk) {
        super(entityType, level);
        Husk = husk;
    }


    @Inject(at = @At("HEAD"),method = "tick")
    private void init(CallbackInfo info) {
        if (this.getType() != EntityType.HUSK)
            if (this.level().getBiome(this.blockPosition()).is(SNOW_GOLEM_MELTS) && this.level().getBiome(this.blockPosition()).is(IS_OVERWORLD) && this.isAlive() && this.level().getDayTime()%24000 >= 0 && this.level().getDayTime()%24000 <= 12000 && this.isOnFire() || this.level().getBiome(this.blockPosition()).is(IS_NETHER) && this.isAlive() && this.isOnFire()
        )   {
                convertToZombieType(EntityType.HUSK);
            }

    }

}
