package com.gildedrose;

import com.gildedrose.rules.AgedBrieRule;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (SULFURAS.equals(item.name)) {
                return;
            }
            if (AGED_BRIE.equals(item.name)) {
                AgedBrieRule.apply(item);

                return;
            }
            if (BACKSTAGE.equals(item.name)) {
                applyBackstageRule(item);

                return;
            }
            applyCommonItemRule(item);
        }
    }

    private static void applyCommonItemRule(Item item) {
        if (item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }

        item.sellIn -= 1;

        if (item.quality > 50) {
            item.quality = 50;
        } else if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private static void applyBackstageRule(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        }else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.quality = Math.min(item.quality, 50);
        item.sellIn--;
    }
}
