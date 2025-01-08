package com.sakalti.blockmob;

import com.sakalti.blockmob.effect.ModEffects;
import com.sakalti.blockmob.registry.ModBlockMobs;
import net.fabricmc.api.ModInitializer;

public class BlockMobMod implements ModInitializer {
    public static final String MOD_ID = "blockmob";

    @Override
    public void onInitialize() {
        // エフェクト登録
        ModEffects.registerEffects();

        // エンティティ登録
        ModBlockMobs.registerEntities();

        System.out.println("BlockMobMod 初期化完了！");
    }
}
