package io.github.prismwork.flexiblearms.mixin.models;

import io.github.prismwork.flexiblearms.util.ArmModelProperties;
import io.github.prismwork.flexiblearms.util.ArmModelsContainer;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({JsonUnbakedModel.class, BasicBakedModel.class})
public abstract class ArmModelsContainerModelsMixin
	implements ArmModelsContainer {
	@Unique
	private ArmModelProperties leftArmProps = new ArmModelProperties();
	@Unique
	private ArmModelProperties rightArmProps = new ArmModelProperties();

	@Override
	public ArmModelProperties getLeftArmModelProperties() {
		return leftArmProps;
	}

	@Override
	public ArmModelProperties getRightArmModelProperties() {
		return rightArmProps;
	}
}
