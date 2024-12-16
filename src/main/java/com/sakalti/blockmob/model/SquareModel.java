package com.sakalti.blockmob.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SquareModel<T> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("blockmob", "square"), "main");
    private final ModelPart body;

    public SquareModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // 特別な動きはなし
    }

    @Override
    public ModelPart getPart() {
        return this.body;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData partData = data.getRoot();
        partData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F), ModelTransform.NONE);
        return TexturedModelData.of(data, 64, 32);
    }
}
