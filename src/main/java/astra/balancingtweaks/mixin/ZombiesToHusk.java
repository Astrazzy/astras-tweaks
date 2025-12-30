package astra.balancingtweaks.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.tags.BiomeTags.IS_NETHER;
import static net.minecraft.tags.BiomeTags.IS_OVERWORLD;
import static net.minecraft.world.attribute.EnvironmentAttributes.SNOW_GOLEM_MELTS;

@Mixin(Zombie.class)
public abstract class ZombiesToHusk extends Entity {

    public ZombiesToHusk(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    protected abstract void convertToZombieType(ServerLevel serverLevel, EntityType<? extends Zombie> entityType);

    @Inject(at = @At("HEAD"),method = "tick")
    private void init(CallbackInfo info) {
        if (this.getType() != EntityType.HUSK) //Checks if the zombie being checked is not a husk which prevents them from repeatedly converting and disappearing
            if (this.level().environmentAttributes().getValue(SNOW_GOLEM_MELTS, this.blockPosition())
                    && this.level().getBiome(this.blockPosition()).is(IS_OVERWORLD)
                    && this.isAlive()
                    && this.level().getDayTime()%24000 >= 0
                    && this.level().getDayTime()%24000 <= 12000
                    && this.isOnFire()
                || this.level().getBiome(this.blockPosition()).is(IS_NETHER)
                    && this.isAlive()
                    && this.isOnFire()
        )   {
                ServerLevel serverLevel = (ServerLevel) this.level();
                this.convertToZombieType(serverLevel, EntityType.HUSK);
            }

    }

}
