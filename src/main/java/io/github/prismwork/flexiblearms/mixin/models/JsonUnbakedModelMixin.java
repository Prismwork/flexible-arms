package io.github.prismwork.flexiblearms.mixin.models;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(JsonUnbakedModel.class)
public abstract class JsonUnbakedModelMixin {
	@ModifyReturnValue(
		method = "bake(Lnet/minecraft/client/render/model/ModelBaker;Lnet/minecraft/client/render/model/json/JsonUnbakedModel;Ljava/util/function/Function;Lnet/minecraft/client/render/model/ModelBakeSettings;Lnet/minecraft/util/Identifier;Z)Lnet/minecraft/client/render/model/BakedModel;",
		at = @At(value = "RETURN", ordinal = 1)
	)
	private BakedModel flexible_arms$processOnBake(BakedModel original) {
		if (!(original instanceof BasicBakedModel)) return original;

		ArmModelsContainer sourceArmModels = (ArmModelsContainer) this;
		ArmModelsContainer targetArmModels = (ArmModelsContainer) original;

		if (sourceArmModels.getLeftArmYaw() != null)
			targetArmModels.setLeftArmYaw(sourceArmModels.getLeftArmYaw());
		if (sourceArmModels.getLeftArmPitch() != null)
			targetArmModels.setLeftArmPitch(sourceArmModels.getLeftArmPitch());
		if (sourceArmModels.leftArmFollowSight() != null)
			targetArmModels.setLeftArmFollowSight(sourceArmModels.leftArmFollowSight());
		if (sourceArmModels.getRightArmYaw() != null)
			targetArmModels.setRightArmYaw(sourceArmModels.getRightArmYaw());
		if (sourceArmModels.getRightArmPitch() != null)
			targetArmModels.setRightArmPitch(sourceArmModels.getRightArmPitch());
		if (sourceArmModels.rightArmFollowSight() != null)
			targetArmModels.setRightArmFollowSight(sourceArmModels.rightArmFollowSight());

		return original;
	}
}
