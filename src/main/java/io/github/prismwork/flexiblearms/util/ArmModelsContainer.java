package io.github.prismwork.flexiblearms.util;

import org.jetbrains.annotations.ApiStatus;

public interface ArmModelsContainer {
	ArmModelProperties getLeftArmModelProperties();

	ArmModelProperties getRightArmModelProperties();

	@ApiStatus.Internal
	final class Dummy implements ArmModelsContainer {
		@Override public ArmModelProperties getLeftArmModelProperties() { return new ArmModelProperties(); }
		@Override public ArmModelProperties getRightArmModelProperties() { return new ArmModelProperties(); }
	}
}
