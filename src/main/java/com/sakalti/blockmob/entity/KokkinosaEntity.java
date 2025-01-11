package com.sakalti.blockmob.entity;

import com.sakalti.blockmob.BlockMobMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class KokkinosaEntity extends PathAwareEntity {

    public KokkinosaEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    // 属性設定
    public static DefaultAttributeContainer.Builder createKokkinosaAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0) // HP
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0) // 攻撃力
                .add(EntityAttributes.GENERIC_ARMOR, 2.0) // 防御力
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3); // 移動速度
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
    }

    // ドロップアイテム
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        Random random = this.getRandom();

        // 鉄インゴットを15%の確率で1～3個
        if (random.nextFloat() < 0.15) {
            this.dropStack(Items.IRON_INGOT.getDefaultStack().copyWithCount(1 + random.nextInt(3)));
        }

        // レッドストーンを14.4%の確率で3～7個
        if (random.nextFloat() < 0.144) {
            this.dropStack(Items.REDSTONE.getDefaultStack().copyWithCount(3 + random.nextInt(5)));
        }
    }

    // サウンド設定
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_GENERIC_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return BlockMobMod.BLOCK_MOB_ATTACK; // カスタムサウンドを使用
    }

    @Override
    protected void playAmbientSound() {
        this.playSound(adjustPitch(this.getAmbientSound(), 0.5f), 1.0F, 0.5F);
    }

    @Override
    protected void playDeathSound() {
        this.playSound(adjustPitch(this.getDeathSound(), 0.8f), 1.0F, 0.8F);
    }

    private SoundEvent adjustPitch(SoundEvent soundEvent, float pitch) {
        return new SoundEvent(soundEvent.getId()) {
            @Override
            public float getPitch() {
                return pitch;
            }
        };
    }
}
