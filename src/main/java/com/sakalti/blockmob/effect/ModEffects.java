package com.sakalti.blockmob.effect;

import com.sakalti.blockmob.BlockMobMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final LuxmarkStatusEffect LUXMARK = new LuxmarkStatusEffect();

    public static void registerEffects() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(BlockMobMod.MOD_ID, "luxmark"), LUXMARK);
        System.out.println("エフェクト登録完了！");
    }
}
