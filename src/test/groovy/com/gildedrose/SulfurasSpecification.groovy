package com.gildedrose

import spock.lang.Specification

class SulfurasSpecification extends Specification {

    def "Sulfuras, independente da situação, sempre terá quality 80"() {
        given:
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, 50)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == 80

        where:
        sellIn | expectedSellIn | quality
        1      | 1              | 50
        0      | 0              | 60
        -2     | -2             | 0
    }
}
