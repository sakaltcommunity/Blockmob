package com.sakalti.blockmob.registry;

import com.sakalti.blockmob.BlockMobMod;
import com.sakalti.blockmob.entity.IgnitzeEntity;
import com.sakalti.blockmob.entity.LuxarEntity;
import com.sakalti.blockmob.entity.KokkinosaEntity;
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

public class ModBlockMobs {
    // エンティティ定義
    public static final EntityType<IgnitzeEntity> IGNITZE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(BlockMobMod.MOD_ID, "ignitze"),
            EntityType.Builder.create(IgnitzeEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.75f, 0.75f)
                    .build("ignitze")
    );

    public static final EntityType<LuxarEntity> LUXAR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(BlockMobMod.MOD_ID, "luxar"),
            EntityType.Builder.create(LuxarEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.75f, 0.75f)
                    .build("luxar")
    );

    public static final EntityType<KokkinosaEntity> KOKKINOSA = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(BlockMobMod.MOD_ID, "kokkinosa"),
            EntityType.Builder.create(KokkinosaEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.75f, 0.75f) // 寸法をそのままに
                    .build("kokkinosa")
    );

    // スポーンエッグ定義
    public static final Item IGNITZE_SPAWN_EGG = new SpawnEggItem(IGNITZE, 0x000000, 0x00FFFF, new Item.Settings());
    public static final Item LUXAR_SPAWN_EGG = new SpawnEggItem(LUXAR, 0x000000, 0xFFA500, new Item.Settings());
    public static final Item KOKKINOSA_SPAWN_EGG = new SpawnEggItem(KOKKINOSA, 0x8B0000, 0xFFD700, new Item.Settings()); // 色はそのまま

    // アイテムグループ
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(BlockMobMod.MOD_ID, "general"))
            .icon(() -> new ItemStack(IGNITZE_SPAWN_EGG))
            .build();

    public static void registerEntities() {
        // エンティティ属性登録
        FabricDefaultAttributeRegistry.register(IGNITZE, IgnitzeEntity.createIgnitzeAttributes());
        FabricDefaultAttributeRegistry.register(LUXAR, LuxarEntity.createLuxarAttributes());
        FabricDefaultAttributeRegistry.register(KOKKINOSA, KokkinosaEntity.createKokkinosaAttributes());

        // スポーンエッグ登録
        Registry.register(Registries.ITEM, new Identifier(BlockMobMod.MOD_ID, "ignitze_spawn_egg"), IGNITZE_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier(BlockMobMod.MOD_ID, "luxar_spawn_egg"), LUXAR_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier(BlockMobMod.MOD_ID, "kokkinosa_spawn_egg"), KOKKINOSA_SPAWN_EGG);

        BlockMobMod.LOGGER.info("エンティティ登録完了！");
    }
}
