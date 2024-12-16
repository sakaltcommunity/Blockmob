package com.sakalti.blockmob.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;

public class LuxmarkStatusEffect extends StatusEffect {
    public LuxmarkStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFA500);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient && entity.age % 20 == 0) {
            entity.damage(entity.getDamageSources().magic(), 2.0F + amplifier);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
