package astra.astrastweaks;

import astra.astrastweaks.enchantment.effect.SpectrumEnchantmentEffect;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {
    public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        System.out.println("REGISTERING ENCHANTS");
    }
    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        // new enchantment Spectrum
        register(entries, ModEnchantmentEffects.SPECTRUM, Enchantment.enchantment(
                                Enchantment.definition(
                                        registries.lookupOrThrow(Registries.ITEM).getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                        // this is the "weight" or probability of our enchantment showing up in the table
                                        3,
                                        // the maximum level of the enchantment
                                        3,
                                        // base cost for level 1 of the enchantment, and min levels required for something higher
                                        Enchantment.dynamicCost(1, 10),
                                        // same fields as above but for max cost
                                        Enchantment.dynamicCost(1, 15),
                                        // anvil cost
                                        5,
                                        // valid slots
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                                // enchantment occurs when the arrow is fired
                                EnchantmentEffectComponents.PROJECTILE_SPAWNED,
                                new SpectrumEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f)) // scale the enchantment linearly.
                        )
        );
    }
    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.location()), resourceConditions);
    }

    @Override
    public String getName() {
        return "ModEnchantmentGenerator";
    }
}
