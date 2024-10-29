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
        item.sellIn == sellIn
        item.quality == quality

        where:
        sellIn | quality
        1      | 50
        0      | 60
        -2     | 0
    }
}
