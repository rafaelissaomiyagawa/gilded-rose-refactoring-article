package com.gildedrose.rules;

import com.gildedrose.Item;

public class AgedBrieRule {
    public static void apply(Item item) {
        if (item.sellIn <= 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.quality = Math.min(item.quality, 50);
        item.sellIn--;
    }
}
