package com.jjhjart.ancientcraft.items;

import com.jjhjart.ancientcraft.NewVoid;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class Cheese extends Item {
    public Cheese() {
        super(new Properties()
                .group(NewVoid.TAB)
                .food(new Food.Builder()
                .hunger(1)
                .saturation(1)
                .build())





        );
    }
}
