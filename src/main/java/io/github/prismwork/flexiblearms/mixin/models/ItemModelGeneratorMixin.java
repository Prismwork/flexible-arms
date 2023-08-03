package io.github.prismwork.flexiblearms.mixin.models;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.render.model.json.ItemModelGenerator;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemModelGenerator.class)
public class ItemModelGeneratorMixin{
	@ModifyReturnValue(
		method = "create",
		at = @At(value = "RETURN", ordinal = 0)
	)
	private JsonUnbakedModel flexible_arms$processGeneratedOnBake(JsonUnbakedModel original, @Local(ordinal = 0) JsonUnbakedModel blockModel) {
		ArmModelsContainer sourceArmModels = (ArmModelsContainer) blockModel;
		ArmModelsContainer targetArmModels = (ArmModelsContainer) original;

		targetArmModels.getLeftArmModelProperties().set(sourceArmModels.getLeftArmModelProperties());
		targetArmModels.getRightArmModelProperties().set(sourceArmModels.getRightArmModelProperties());

		return original;
	}
}
