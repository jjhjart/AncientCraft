package com.jjhjart.ancientcraft.items;

import com.jjhjart.ancientcraft.NewVoid;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class CookedHog extends Item {
    public CookedHog() {
        super(new Properties()
                .group(NewVoid.TAB)
                .food(new Food.Builder()
                .hunger(8)
                .saturation(3.2F)
                .build())





        );
    }
}
