package com.jjhjart.ancientcraft.items;

import com.jjhjart.ancientcraft.NewVoid;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class RawHog extends Item {
    public RawHog() {
        super(new Item.Properties()
                .group(NewVoid.TAB)
                .food(new Food.Builder()
                .hunger(4)
                .saturation(1.2F)
                .build())





        );
    }
}
