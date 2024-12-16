package com.sakalti.blockmob.renderer;

import com.sakalti.blockmob.entity.LuxarEntity;
import com.sakalti.blockmob.model.SquareModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class LuxarRenderer extends MobEntityRenderer<LuxarEntity, SquareModel<LuxarEntity>> {
    private static final Identifier TEXTURE = new Identifier("blockmob", "textures/entity/luxar.png");

    public LuxarRenderer(EntityRendererFactory.Context context) {
        super(context, new SquareModel<>(context.getPart(SquareModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(LuxarEntity entity) {
        return TEXTURE;
    }
}
