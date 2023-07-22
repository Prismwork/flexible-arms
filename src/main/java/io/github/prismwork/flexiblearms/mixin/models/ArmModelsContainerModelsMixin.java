package io.github.prismwork.flexiblearms.mixin.models;

import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({JsonUnbakedModel.class, BasicBakedModel.class})
public abstract class ArmModelsContainerModelsMixin
	implements ArmModelsContainer {
	@Unique
	private Float leftArmYaw = null;
	@Unique
	private Float leftArmPitch = null;
	@Unique
	private Float rightArmYaw = null;
	@Unique
	private Float rightArmPitch = null;
	@Unique
	private Boolean leftArmFollowSight = null;
	@Unique
	private Boolean rightArmFollowSight = null;

	@Override
	public Float getLeftArmYaw() {
		return leftArmYaw;
	}

	@Override
	public Float getLeftArmPitch() {
		return leftArmPitch;
	}

	@Override
	public Float getRightArmYaw() {
		return rightArmYaw;
	}

	@Override
	public Float getRightArmPitch() {
		return rightArmPitch;
	}

	@Override
	public Boolean leftArmFollowSight() {
		return leftArmFollowSight;
	}

	@Override
	public Boolean rightArmFollowSight() {
		return rightArmFollowSight;
	}

	@Override
	public void setLeftArmYaw(float yaw) {
		this.leftArmYaw = yaw;
	}

	@Override
	public void setLeftArmPitch(float pitch) {
		this.leftArmPitch = pitch;
	}

	@Override
	public void setRightArmYaw(float yaw) {
		this.rightArmYaw = yaw;
	}

	@Override
	public void setRightArmPitch(float pitch) {
		this.rightArmPitch = pitch;
	}

	@Override
	public void setLeftArmFollowSight(boolean value) {
		this.leftArmFollowSight = value;
	}

	@Override
	public void setRightArmFollowSight(boolean value) {
		this.rightArmFollowSight = value;
	}
}
