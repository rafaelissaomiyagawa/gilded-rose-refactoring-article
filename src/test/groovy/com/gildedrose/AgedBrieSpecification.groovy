package com.gildedrose

import com.gildedrose.rules.AgedBrieRule
import spock.lang.Specification

class AgedBrieSpecification extends Specification {

    def "Aged Brie aumenta sua qualidade em 1 unidade a medida que envelhece."() {
        given:
        Item item = new Item("Aged Brie", sellIn, quality)

        when:
        AgedBrieRule.apply(item)

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        10     | 9              | 10      | 11
        5      | 4              | 0       | 1
        1      | 0              | 49      | 50
    }

    def "Aged Brie aumenta sua qualidade em 2 se sellin é menor ou igual a zero"() {
        given:
        Item item = new Item("Aged Brie", sellIn, quality)

        when:
        AgedBrieRule.apply(item)

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        0      | -1             | 10      | 12
        -1     | -2             | 1       | 3
        -2     | -3             | 48      | 50
    }

    def "Aged Brie não pode ter quality maior que 50"() {
        given:
        Item item = new Item("Aged Brie", sellIn, quality)

        when:
        AgedBrieRule.apply(item)

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        1      | 0              | 50      | 50
        -2     | -3             | 49      | 50
    }
}
