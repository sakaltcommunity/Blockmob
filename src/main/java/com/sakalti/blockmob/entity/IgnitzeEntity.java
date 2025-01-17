package com.sakalti.blockmob.entity;

import com.sakalti.blockmob.effect.ModEffects;
import com.sakalti.blockmob.Mod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

public class IgnitzeEntity extends PathAwareEntity {

    public IgnitzeEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    // 属性の設定
    public static DefaultAttributeContainer.Builder createIgnitzeAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)  // HP
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)  // 攻撃力
                .add(EntityAttributes.GENERIC_ARMOR, 5.0)  // 防御力
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);  // 移動速度
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
    }

    // ドロップアイテム
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        if (this.random.nextFloat() < 0.25) {  // 25%の確率で金インゴットを2-4個
            int amount = 2 + this.random.nextInt(3);
            this.dropItem(Items.GOLD_INGOT, amount);
        }
    }

    // スポーン条件（夜間にスポーン）
    @Override
    public boolean canSpawn(ServerWorld world, boolean spawnReason) {
        return world.isNight() && super.canSpawn(world, spawnReason);
    }
}
