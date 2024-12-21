package com.sakalti.blockmob;

import com.sakalti.blockmob.effect.ModEffects;
import com.sakalti.blockmob.entity.IgnitzeEntity;
import com.sakalti.blockmob.entity.LuxarEntity;
import com.sakalti.blockmob.renderer.IgnitzeRenderer;
import com.sakalti.blockmob.renderer.LuxarRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockMobMod implements ModInitializer {
    public static final String MOD_ID = "blockmob";

    // エンティティ定義
    public static final EntityType<IgnitzeEntity> IGNITZE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "ignitze"),
            EntityType.Builder.create(IgnitzeEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.75f, 0.75f)
                    .build("ignitze")
    );

    public static final EntityType<LuxarEntity> LUXAR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "luxar"),
            EntityType.Builder.create(LuxarEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.75f, 0.75f)
                    .build("luxar")
    );

    // スポーンエッグ定義
    public static final Item IGNITZE_SPAWN_EGG = new SpawnEggItem(IGNITZE, 0x000000, 0x00FFFF, new Item.Settings());
    public static final Item LUXAR_SPAWN_EGG = new SpawnEggItem(LUXAR, 0x000000, 0xFFA500, new Item.Settings());

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "general"))
            .icon(() -> new ItemStack(IGNITZE_SPAWN_EGG))
            .build();

    @Override
    public void onInitialize() {
        // エフェクト登録
        ModEffects.registerEffects();

        // 属性登録
        FabricDefaultAttributeRegistry.register(IGNITZE, IgnitzeEntity.createIgnitzeAttributes());
        FabricDefaultAttributeRegistry.register(LUXAR, LuxarEntity.createLuxarAttributes());
        Registry.register(Registry.SOUND_EVENT, BLOCK_MOB_ATTACK_ID, BLOCK_MOB_ATTACK);
        // スポーンエッグ登録
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ignitze_spawn_egg"), IGNITZE_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "luxar_spawn_egg"), LUXAR_SPAWN_EGG);

        System.out.println("BlockMobMod 初期化完了！");
    }
}
