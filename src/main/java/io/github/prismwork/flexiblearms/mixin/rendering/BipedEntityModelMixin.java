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
	@Shadow
	public @Final ModelPart rightArm;

	@Shadow
	public @Final ModelPart leftArm;

	@Shadow
	@Final
	public ModelPart head;

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

		boolean rightArmFollowSightOnRightModel = rightHandModel.rightArmFollowSight() != null ?
			rightHandModel.rightArmFollowSight() : true;
		if (rightHandModel.getRightArmYaw() != null)
			this.rightArm.yaw = rightArmFollowSightOnRightModel ?
				rightHandModel.getRightArmYaw() + this.head.yaw :
				rightHandModel.getRightArmYaw();
		if (rightHandModel.getRightArmPitch() != null)
			this.rightArm.pitch = rightArmFollowSightOnRightModel ?
				rightHandModel.getRightArmPitch() + this.head.pitch :
				rightHandModel.getRightArmPitch();

		boolean leftArmFollowSightOnRightModel = rightHandModel.leftArmFollowSight() != null ?
			rightHandModel.leftArmFollowSight() : true;
		if (rightHandModel.getLeftArmYaw() != null)
			this.leftArm.yaw = leftArmFollowSightOnRightModel ?
				rightHandModel.getLeftArmYaw() + this.head.yaw :
				rightHandModel.getLeftArmYaw();
		if (rightHandModel.getLeftArmPitch() != null)
			this.leftArm.pitch = leftArmFollowSightOnRightModel ?
				rightHandModel.getLeftArmPitch() + this.head.pitch :
				rightHandModel.getLeftArmPitch();

		if (
			rightHandModel.getRightArmYaw() != null ||
			rightHandModel.getRightArmPitch() != null ||
			rightHandModel.getLeftArmYaw() != null ||
			rightHandModel.getLeftArmPitch() != null
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

		boolean rightArmFollowSightOnLeftModel = leftHandModel.rightArmFollowSight() != null ?
			leftHandModel.rightArmFollowSight() : true;
		if (leftHandModel.getRightArmYaw() != null && rightHandModel.getRightArmYaw() == null)
			this.rightArm.yaw = rightArmFollowSightOnLeftModel ?
				leftHandModel.getRightArmYaw() + this.head.yaw :
				leftHandModel.getRightArmYaw();
		if (leftHandModel.getRightArmPitch() != null && rightHandModel.getRightArmPitch() == null)
			this.rightArm.pitch = rightArmFollowSightOnLeftModel ?
				leftHandModel.getRightArmPitch() + this.head.pitch :
				leftHandModel.getRightArmPitch();

		boolean leftArmFollowSightOnLeftModel = leftHandModel.leftArmFollowSight() != null ?
			leftHandModel.leftArmFollowSight() : true;
		if (leftHandModel.getLeftArmYaw() != null && rightHandModel.getLeftArmYaw() == null)
			this.leftArm.yaw = leftArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmYaw() + this.head.yaw :
				leftHandModel.getLeftArmYaw();
		if (leftHandModel.getLeftArmPitch() != null && rightHandModel.getLeftArmPitch() == null)
			this.leftArm.pitch = leftArmFollowSightOnLeftModel ?
				leftHandModel.getLeftArmPitch() + this.head.pitch :
				leftHandModel.getLeftArmPitch();

		if (
			(leftHandModel.getRightArmYaw() != null && rightHandModel.getRightArmYaw() == null) ||
			(leftHandModel.getRightArmPitch() != null && rightHandModel.getRightArmPitch() == null) ||
			(leftHandModel.getLeftArmYaw() != null && rightHandModel.getLeftArmYaw() == null) ||
			(leftHandModel.getLeftArmPitch() != null && rightHandModel.getLeftArmPitch() == null)
		) ci.cancel();
	}
}
