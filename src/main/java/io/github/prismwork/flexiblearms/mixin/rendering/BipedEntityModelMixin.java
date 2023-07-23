package io.github.prismwork.flexiblearms.mixin.rendering;

import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin {
	@Shadow public @Final ModelPart rightArm;
	@Shadow public @Final ModelPart leftArm;
	@Shadow public @Final ModelPart head;

	@Inject(
		method = "positionRightArm",
		at = @At("HEAD"),
		cancellable = true
	)
	private void flexible_arms$poseRightArm(LivingEntity entity, CallbackInfo ci) {
		ItemModels models = MinecraftClient.getInstance().getItemRenderer().getModels();

		BakedModel rightHandBakedModel = models.getModel(entity.getMainHandStack());

		if (!(rightHandBakedModel instanceof BasicBakedModel)) return;

		ArmModelsContainer rightHandModel = (ArmModelsContainer) rightHandBakedModel;

		boolean rightArmFollowSightOnRightModel = rightHandModel.getRightArmModelProperties().rightArmFollowSight() != null ?
			rightHandModel.getRightArmModelProperties().rightArmFollowSight() : true;
		if (rightHandModel.getRightArmModelProperties().getRightArmYaw() != null)
			this.rightArm.yaw = rightArmFollowSightOnRightModel ?
				rightHandModel.getRightArmModelProperties().getRightArmYaw() + this.head.yaw :
				rightHandModel.getRightArmModelProperties().getRightArmYaw();
		if (rightHandModel.getRightArmModelProperties().getRightArmPitch() != null)
			this.rightArm.pitch = rightArmFollowSightOnRightModel ?
				rightHandModel.getRightArmModelProperties().getRightArmPitch() + this.head.pitch :
				rightHandModel.getRightArmModelProperties().getRightArmPitch();
		if (rightHandModel.getRightArmModelProperties().getRightArmRoll() != null)
			this.rightArm.roll = rightHandModel.getRightArmModelProperties().getRightArmRoll();

		boolean leftArmFollowSightOnRightModel = rightHandModel.getRightArmModelProperties().leftArmFollowSight() != null ?
			rightHandModel.getRightArmModelProperties().leftArmFollowSight() : true;
		if (rightHandModel.getRightArmModelProperties().getLeftArmYaw() != null)
			this.leftArm.yaw = leftArmFollowSightOnRightModel ?
				rightHandModel.getRightArmModelProperties().getLeftArmYaw() + this.head.yaw :
				rightHandModel.getRightArmModelProperties().getLeftArmYaw();
		if (rightHandModel.getRightArmModelProperties().getLeftArmPitch() != null)
			this.leftArm.pitch = leftArmFollowSightOnRightModel ?
				rightHandModel.getRightArmModelProperties().getLeftArmPitch() + this.head.pitch :
				rightHandModel.getRightArmModelProperties().getLeftArmPitch();
		if (rightHandModel.getRightArmModelProperties().getLeftArmRoll() != null)
			this.leftArm.roll = rightHandModel.getRightArmModelProperties().getLeftArmRoll();

		if (
			rightHandModel.getRightArmModelProperties().getRightArmYaw() != null ||
			rightHandModel.getRightArmModelProperties().getRightArmPitch() != null ||
			rightHandModel.getRightArmModelProperties().getRightArmRoll() != null ||
			rightHandModel.getRightArmModelProperties().getLeftArmYaw() != null ||
			rightHandModel.getRightArmModelProperties().getLeftArmPitch() != null ||
			rightHandModel.getRightArmModelProperties().getLeftArmRoll() != null
		) ci.cancel();
	}

	@Inject(
		method = "positionLeftArm",
		at = @At("HEAD"),
		cancellable = true
	)
	private void flexible_arms$poseLeftArm(LivingEntity entity, CallbackInfo ci) {
		ItemModels models = MinecraftClient.getInstance().getItemRenderer().getModels();

		BakedModel rightHandBakedModel = models.getModel(entity.getMainHandStack());
		BakedModel leftHandBakedModel = models.getModel(entity.getOffHandStack());

		if (!(leftHandBakedModel instanceof BasicBakedModel)) return;

		ArmModelsContainer rightHandModel = (rightHandBakedModel instanceof BasicBakedModel) ?
			(ArmModelsContainer) rightHandBakedModel :
			new ArmModelsContainer.Dummy();
		ArmModelsContainer leftHandModel = (ArmModelsContainer) leftHandBakedModel;

		boolean rightArmFollowSightOnLeftModel = leftHandModel.getLeftArmModelProperties().rightArmFollowSight() != null ?
			leftHandModel.getLeftArmModelProperties().rightArmFollowSight() : true;
		if (leftHandModel.getLeftArmModelProperties().getRightArmYaw() != null && rightHandModel.getRightArmModelProperties().getRightArmYaw() == null)
			this.rightArm.yaw = rightArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmModelProperties().getRightArmYaw() + this.head.yaw :
				leftHandModel.getLeftArmModelProperties().getRightArmYaw();
		if (leftHandModel.getLeftArmModelProperties().getRightArmPitch() != null && rightHandModel.getRightArmModelProperties().getRightArmPitch() == null)
			this.rightArm.pitch = rightArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmModelProperties().getRightArmPitch() + this.head.pitch :
				leftHandModel.getLeftArmModelProperties().getRightArmPitch();
		if (leftHandModel.getLeftArmModelProperties().getRightArmRoll() != null && rightHandModel.getRightArmModelProperties().getRightArmRoll() == null)
			this.rightArm.roll = leftHandModel.getLeftArmModelProperties().getRightArmRoll();

		boolean leftArmFollowSightOnLeftModel = leftHandModel.getLeftArmModelProperties().leftArmFollowSight() != null ?
			leftHandModel.getLeftArmModelProperties().leftArmFollowSight() : true;
		if (leftHandModel.getLeftArmModelProperties().getLeftArmYaw() != null && rightHandModel.getRightArmModelProperties().getLeftArmYaw() == null)
			this.leftArm.yaw = leftArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmModelProperties().getLeftArmYaw() + this.head.yaw :
				leftHandModel.getLeftArmModelProperties().getLeftArmYaw();
		if (leftHandModel.getLeftArmModelProperties().getLeftArmPitch() != null && rightHandModel.getRightArmModelProperties().getLeftArmPitch() == null)
			this.leftArm.pitch = leftArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmModelProperties().getLeftArmPitch() + this.head.pitch :
				leftHandModel.getLeftArmModelProperties().getLeftArmPitch();
		if (leftHandModel.getLeftArmModelProperties().getLeftArmRoll() != null && rightHandModel.getRightArmModelProperties().getLeftArmRoll() == null)
			this.leftArm.roll = leftHandModel.getLeftArmModelProperties().getLeftArmRoll();

		if (
			(leftHandModel.getLeftArmModelProperties().getRightArmYaw() != null && rightHandModel.getRightArmModelProperties().getRightArmYaw() == null) ||
			(leftHandModel.getLeftArmModelProperties().getRightArmPitch() != null && rightHandModel.getRightArmModelProperties().getRightArmPitch() == null) ||
			(leftHandModel.getLeftArmModelProperties().getRightArmRoll() != null && rightHandModel.getRightArmModelProperties().getRightArmRoll() == null) ||
			(leftHandModel.getLeftArmModelProperties().getLeftArmYaw() != null && rightHandModel.getRightArmModelProperties().getLeftArmYaw() == null) ||
			(leftHandModel.getLeftArmModelProperties().getLeftArmPitch() != null && rightHandModel.getRightArmModelProperties().getLeftArmPitch() == null) ||
			(leftHandModel.getLeftArmModelProperties().getLeftArmRoll() != null && rightHandModel.getRightArmModelProperties().getLeftArmRoll() == null)
		) ci.cancel();
	}
}
