package com.sakalti.blockmob.entity;

import com.sakalti.blockmob.effect.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.potion.StatusEffectInstance;

public class LuxarEntity extends PathAwareEntity {

    public LuxarEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    // 属性の設定
    public static DefaultAttributeContainer.Builder createLuxarAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)  // HP
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)  // 攻撃力
                .add(EntityAttributes.GENERIC_ARMOR, 9.0)  // 防御力
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.48);  // 移動速度
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.LUXMARK, 100, 3));
        }
    }
}
