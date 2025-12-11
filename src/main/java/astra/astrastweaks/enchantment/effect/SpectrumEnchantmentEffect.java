package astra.astrastweaks.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record SpectrumEnchantmentEffect(LevelBasedValue amount) implements EnchantmentEntityEffect{
    public static final MapCodec<SpectrumEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    LevelBasedValue.CODEC.fieldOf("amount").forGetter(SpectrumEnchantmentEffect::amount)
            ).apply(instance, SpectrumEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity entity, Vec3 pos) {
        float speedBoost = (float) (1.25f + (0.5*(level-1)));
        entity.setNoGravity(true);
        entity.setDeltaMovement(speedBoost*entity.getDeltaMovement().x,speedBoost*entity.getDeltaMovement().y,speedBoost*entity.getDeltaMovement().z);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
