package astra.astrastweaks;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AstrasTweaks implements ModInitializer {
	public static final String MOD_ID = "astras-tweaks";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        UseItemCallback.EVENT.register((player, Level, hand) -> {
            if (Level instanceof ServerLevel serverLevel) {
                if (player.isFallFlying()) {
                    if (player.getMainHandItem().getItem() == Items.FIREWORK_ROCKET || player.getOffhandItem().getItem() == Items.FIREWORK_ROCKET) { //checks if the item used was a firework rocket in the main or offhand
                        player.getItemBySlot(EquipmentSlot.CHEST).hurtWithoutBreaking(10, player); //Removes the durability from the elytra
                        player.causeFoodExhaustion(12); //12 does not refer the number of hunger bars, a hunger bar is 4 of this value but will target saturation if it is available instead
                    }
                }
            }
            return InteractionResult.PASS;
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(entity instanceof Horse horse && horse.isTamed()) {//checks if the entity is a horse and if its tamed
                if(player.getMainHandItem().getItem() == Items.GOLDEN_CARROT && !horse.getTags().contains("c:golden_carrot_fed") || player.getOffhandItem().getItem() == Items.GOLDEN_CARROT && !horse.getTags().contains("c:golden_carrot_fed")) { //checks if the item used is a golden carrot in main or offhand, and if the horse doesn't have the tag so the resetlove method can't be used to infinitely breed horses
                    horse.addTag("c:golden_carrot_fed"); //tag indicating that this buff is active so that when fed a golden apple the second buff can activate
                    horse.addEffect(new MobEffectInstance(MobEffects.SPEED, -1, 1, false, false, false));
                    horse.resetLove(); //allows you to activate this buff if the horse has already been recently fed
                    }
                else if (player.getMainHandItem().getItem() == Items.GOLDEN_APPLE && horse.getTags().contains("c:golden_carrot_fed") && !horse.getTags().contains("c:golden_apple_fed") || player.getOffhandItem().getItem() == Items.GOLDEN_APPLE && horse.getTags().contains("c:golden_carrot_fed") && !horse.getTags().contains("c:golden_apple_fed")) { //checks if item is golden apple in main or offhand, and if the previous buff has been applied and if the golden apple buff already is in use so resetlove method doesn't allow the infinite breeding of horses
                    horse.addTag("c:golden_apple_fed"); //tag indicating this buff is active
                    horse.addEffect(new MobEffectInstance(MobEffects.SPEED,-1, 2,false,false,false));
                    horse.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST,-1,1,false,false,false));
                    horse.resetLove(); //allows you to activate this buff if the horse has already been recently fed

                }
            }


            return InteractionResult.PASS;
        });
    }}