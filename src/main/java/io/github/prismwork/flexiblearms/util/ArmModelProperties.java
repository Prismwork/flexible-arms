package io.github.prismwork.flexiblearms.util;

import com.google.gson.JsonObject;

public final class ArmModelProperties {
	private Float leftArmYaw;
	private Float leftArmPitch;
	private Float leftArmRoll;
	private Float rightArmYaw;
	private Float rightArmPitch;
	private Float rightArmRoll;
	private Boolean leftArmFollowSight;
	private Boolean rightArmFollowSight;

	public ArmModelProperties() {
		leftArmYaw = null;
		leftArmPitch = null;
		leftArmRoll = null;
		rightArmYaw = null;
		rightArmPitch = null;
		rightArmRoll = null;
		leftArmFollowSight = null;
		rightArmFollowSight = null;
	}

	public Float getLeftArmYaw() {
		return leftArmYaw;
	}

	public Float getLeftArmPitch() {
		return leftArmPitch;
	}

	public Float getRightArmYaw() {
		return rightArmYaw;
	}

	public Float getRightArmPitch() {
		return rightArmPitch;
	}

	public Boolean leftArmFollowSight() {
		return leftArmFollowSight;
	}

	public Boolean rightArmFollowSight() {
		return rightArmFollowSight;
	}

	public void setLeftArmYaw(float yaw) {
		this.leftArmYaw = yaw;
	}

	public void setLeftArmPitch(float pitch) {
		this.leftArmPitch = pitch;
	}

	public void setRightArmYaw(float yaw) {
		this.rightArmYaw = yaw;
	}

	public void setRightArmPitch(float pitch) {
		this.rightArmPitch = pitch;
	}

	public void setLeftArmFollowSight(boolean value) {
		this.leftArmFollowSight = value;
	}

	public void setRightArmFollowSight(boolean value) {
		this.rightArmFollowSight = value;
	}

	public Float getLeftArmRoll() {
		return leftArmRoll;
	}

	public void setLeftArmRoll(float leftArmRoll) {
		this.leftArmRoll = leftArmRoll;
	}

	public Float getRightArmRoll() {
		return rightArmRoll;
	}

	public void setRightArmRoll(float rightArmRoll) {
		this.rightArmRoll = rightArmRoll;
	}

	public void set(ArmModelProperties that) {
		this.leftArmYaw = that.leftArmYaw;
		this.leftArmPitch = that.leftArmPitch;
		this.leftArmRoll = that.leftArmRoll;
		this.leftArmFollowSight = that.leftArmFollowSight;
		this.rightArmYaw = that.rightArmYaw;
		this.rightArmPitch = that.rightArmPitch;
		this.rightArmRoll = that.rightArmRoll;
		this.rightArmFollowSight = that.rightArmFollowSight;
	}

	public void setFromJson(JsonObject json) {
		if (json.has("left_arm")) {
			JsonObject leftArm = json.getAsJsonObject("left_arm");

			if (leftArm.has("yaw")) leftArmYaw = leftArm.get("yaw").getAsFloat();
			if (leftArm.has("pitch")) leftArmPitch = leftArm.get("pitch").getAsFloat();
			if (leftArm.has("roll")) leftArmRoll = leftArm.get("roll").getAsFloat();
			if (leftArm.has("follow_sight"))
				leftArmFollowSight = leftArm.get("follow_sight").getAsBoolean();
		}

		if (json.has("right_arm")) {
			JsonObject rightArm = json.getAsJsonObject("right_arm");

			if (rightArm.has("yaw")) rightArmYaw = rightArm.get("yaw").getAsFloat();
			if (rightArm.has("pitch")) rightArmPitch = rightArm.get("pitch").getAsFloat();
			if (rightArm.has("roll")) rightArmRoll = rightArm.get("roll").getAsFloat();
			if (rightArm.has("follow_sight"))
				rightArmFollowSight = rightArm.get("follow_sight").getAsBoolean();
		}
	}
}
