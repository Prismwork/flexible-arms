package io.github.prismwork.flexiblearms.mixin.models;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.prismwork.flexiblearms.util.ArmModelProperties;
import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(JsonUnbakedModel.Deserializer.class)
public abstract class JsonUnbakedModelDeserializerMixin {
	@ModifyReturnValue(method = "deserialize", at = @At("RETURN"))
	private JsonUnbakedModel flexible_arms$postProcessingArms(
		JsonUnbakedModel original,
		@Local(ordinal = 0) JsonObject json
	) {
		ArmModelProperties leftArm = new ArmModelProperties();
		ArmModelProperties rightArm = new ArmModelProperties();

		if (json.has("arm_properties")) {
			JsonObject armProperties = json.getAsJsonObject("arm_properties");
			if (armProperties.has("held_mainhand")) {
				rightArm.setFromJson(armProperties.getAsJsonObject("held_mainhand"));
			}
			if (armProperties.has("held_offhand")) {
				leftArm.setFromJson(armProperties.getAsJsonObject("held_offhand"));
			}
		}

		ArmModelsContainer armModels = (ArmModelsContainer) original;

		armModels.getLeftArmModelProperties().set(leftArm);
		armModels.getRightArmModelProperties().set(rightArm);

		return original;
	}
}
