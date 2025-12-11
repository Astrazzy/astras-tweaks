package astra.astrastweaks;

import astra.astrastweaks.enchantment.effect.SpectrumEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class ModEnchantmentEffects {
    public static final ResourceKey<Enchantment> SPECTRUM = of("spectrum");
    public static final MapCodec<SpectrumEnchantmentEffect> SPECTRUM_EFFECT = register("spectrum_effect", SpectrumEnchantmentEffect.CODEC);

    private static ResourceKey<Enchantment> of(String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AstrasTweaks.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }
    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ResourceLocation.fromNamespaceAndPath(AstrasTweaks.MOD_ID, id), codec);
    }
    public static void registerModEnchantmentEffects() {
        AstrasTweaks.LOGGER.info("Registering Enchantments for" + AstrasTweaks.MOD_ID);
    }
}
