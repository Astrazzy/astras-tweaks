package astra.astrastweaks;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AstrasTweaks implements ModInitializer {
	public static final String MOD_ID = "astras-tweaks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        UseItemCallback.EVENT.register((player, Level, hand) -> {
            if(Level instanceof ServerLevel serverLevel) {
                if(player.isFallFlying()) {
                    if(player.getMainHandItem().getItem() == Items.FIREWORK_ROCKET || player.getOffhandItem().getItem() == Items.FIREWORK_ROCKET) {
                        player.getItemBySlot(EquipmentSlot.CHEST).hurtWithoutBreaking(10, player);
                        player.causeFoodExhaustion(12); //12 does not refer the number of hunger bars, a hunger bar is 4 of this value but will target saturation if it is available instead
                    }
                }
            }
            return InteractionResult.PASS;
        });
        }

    }