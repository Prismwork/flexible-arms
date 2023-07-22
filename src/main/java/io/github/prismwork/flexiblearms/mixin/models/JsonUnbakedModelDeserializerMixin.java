package io.github.prismwork.flexiblearms.mixin.models;

import com.google.gson.JsonObject;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
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
		Float leftArmYaw = null;
		Float leftArmPitch = null;
		Float rightArmYaw = null;
		Float rightArmPitch = null;
		Boolean leftArmFollowSight = null;
		Boolean rightArmFollowSight = null;

		if (json.has("arms")) {
			JsonObject armsObject = json.getAsJsonObject("arms");

			if (armsObject.has("left")) {
				JsonObject leftArmObject = armsObject.getAsJsonObject("left");

				if (leftArmObject.has("yaw")) leftArmYaw = leftArmObject.get("yaw").getAsFloat();
				if (leftArmObject.has("pitch")) leftArmPitch = leftArmObject.get("pitch").getAsFloat();
				if (leftArmObject.has("follow_sight")) leftArmFollowSight = leftArmObject.get("follow_sight").getAsBoolean();
			}

			if (armsObject.has("right")) {
				JsonObject rightArmObject = armsObject.getAsJsonObject("right");

				if (rightArmObject.has("yaw")) rightArmYaw = rightArmObject.get("yaw").getAsFloat();
				if (rightArmObject.has("pitch")) rightArmPitch = rightArmObject.get("pitch").getAsFloat();
				if (rightArmObject.has("follow_sight")) rightArmFollowSight = rightArmObject.get("follow_sight").getAsBoolean();
			}
		}

		ArmModelsContainer armModels = (ArmModelsContainer) original;

		if (leftArmYaw != null) armModels.setLeftArmYaw(leftArmYaw);
		if (leftArmPitch != null) armModels.setLeftArmPitch(leftArmPitch);
		if (leftArmFollowSight != null) armModels.setLeftArmFollowSight(leftArmFollowSight);
		if (rightArmYaw != null) armModels.setRightArmYaw(rightArmYaw);
		if (rightArmPitch != null) armModels.setRightArmPitch(rightArmPitch);
		if (rightArmFollowSight != null) armModels.setRightArmFollowSight(rightArmFollowSight);

		return original;
	}
}
