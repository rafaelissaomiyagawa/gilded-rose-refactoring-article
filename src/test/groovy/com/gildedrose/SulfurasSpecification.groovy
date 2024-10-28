package com.gildedrose

import spock.lang.Specification

class SulfurasSpecification extends Specification {

    def "Sulfuras, independente da situação, nunca altera o sellin e o quality"() {
        given:
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == quality

        where:
        sellIn | expectedSellIn | quality
        1      | 1              | 50
        0      | 0              | 60
        -2     | -2             | 0
    }
}
