package com.sakalti.blockmob.renderer;

import com.sakalti.blockmob.entity.KokkinosaEntity;
import com.sakalti.blockmob.model.SquareModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class KokkinosaRenderer extends MobEntityRenderer<KokkinosaEntity, SquareModel<KokkinosaEntity>> {
    private static final Identifier TEXTURE = new Identifier("blockmob", "textures/entity/kokkinosa.png");

    public KokkinosaRenderer(EntityRendererFactory.Context context) {
        super(context, new SquareModel<>(context.getPart(SquareModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(KokkinosaEntity entity) {
        return TEXTURE;
    }
}
