package io.github.prismwork.flexiblearms.util;

import org.jetbrains.annotations.ApiStatus;

public interface ArmModelsContainer {
	Float getLeftArmYaw();

	Float getLeftArmPitch();

	Float getRightArmYaw();

	Float getRightArmPitch();

	Boolean leftArmFollowSight();

	Boolean rightArmFollowSight();

	void setLeftArmYaw(float yaw);

	void setLeftArmPitch(float pitch);

	void setRightArmYaw(float yaw);

	void setRightArmPitch(float pitch);

	void setLeftArmFollowSight(boolean value);

	void setRightArmFollowSight(boolean value);

	@ApiStatus.Internal
	final class Dummy implements ArmModelsContainer {
		@Override public Float getLeftArmYaw() { return null; }
		@Override public Float getLeftArmPitch() { return null; }
		@Override public Float getRightArmYaw() { return null; }
		@Override public Float getRightArmPitch() { return null; }
		@Override public Boolean leftArmFollowSight() { return null; }
		@Override public Boolean rightArmFollowSight() { return null; }
		@Override public void setLeftArmYaw(float yaw) {}
		@Override public void setLeftArmPitch(float pitch) {}
		@Override public void setRightArmYaw(float yaw) {}
		@Override public void setRightArmPitch(float pitch) {}
		@Override public void setLeftArmFollowSight(boolean value) {}
		@Override public void setRightArmFollowSight(boolean value) {}
	}
}
