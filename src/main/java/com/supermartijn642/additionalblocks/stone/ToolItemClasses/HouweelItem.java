package com.supermartijn642.additionalblocks.stone.ToolItemClasses;

import com.supermartijn642.additionalblocks.stone.IConfigObject;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;

import java.util.function.Supplier;

public class HouweelItem extends PickaxeItem implements IConfigObject {

    private final Supplier<Boolean> enable;

    public HouweelItem(String registryName, Supplier<Boolean> enable, Properties properties, int attackDamage, float attackSpeed) {
        super(ItemTier.IRON, attackDamage, attackSpeed, properties);
        this.setRegistryName(registryName);
        this.enable = enable;
    }

    @Override
    public boolean isEnabled() {
        return this.enable.get();
    }
}