package com.sakalti.blockmob.entity;

import com.sakalti.blockmob.effect.ModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.potion.StatusEffectInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

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
        this.goalSelector.add(4, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true));
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.LUXMARK, 100, 3));
        }
    }

    // ドロップアイテム
    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        super.dropLoot(source, causedByPlayer);
        if (this.random.nextFloat() < 0.094) {  // 9.4%でダイヤを1個
            this.dropItem(Items.DIAMOND);
        }
    }

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
        return BlockMobMod.BLOCK_MOB_ATTACK;
    }

    @Override
    protected void playAmbientSound() {
        this.playSound(adjustPitch(this.getAmbientSound(), 0.4f), 1.0F, 0.4F);
    }

    @Override
    protected void playDeathSound() {
        this.playSound(adjustPitch(this.getDeathSound(), 0.7f), 1.0F, 0.7F);
    }

    private SoundEvent adjustPitch(SoundEvent soundEvent, float pitch) {
        return new SoundEvent(soundEvent.getId()) {
            @Override
            public float getPitch() {
                return pitch;
            }
        };
    }

    // スポーン条件（夜間にスポーン）
    @Override
    public boolean canSpawn(ServerWorld world, boolean spawnReason) {
        return world.isNight() && super.canSpawn(world, spawnReason);
    }
}
