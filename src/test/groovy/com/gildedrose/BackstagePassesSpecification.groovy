package com.gildedrose

import spock.lang.Specification

class BackstagePassesSpecification extends Specification {

    def "Quality aumenta em 1 unidades quando sellIn é maior que 10"() {
        given:
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        11     | 10             | 10      | 11
        15     | 14             | 0       | 1
        20     | 19             | 49      | 50
    }

    def "Quality aumenta em 2 unidades quando sellIn é menor ou igual a 10"() {
        given:
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        10     | 9              | 10      | 12
        6      | 5              | 0       | 2
        6      | 5              | 48      | 50
    }

    def "Quality aumenta em 3 unidades quando sellIn é menor ou igual a 5"() {
        given:
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        5      | 4              | 10      | 13
        3      | 2              | 0       | 3
        1      | 0              | 47      | 50
    }

    def "Quality vai a 0 quando sellIn é igual ou menor que zero"() {
        given:
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        0      | -1             | 10      | 0
        -1     | -2             | 0       | 0
        -5     | 0 - 6          | 47      | 0
    }

    def "Quality nao pode passar de 50, independente se incrementa 1, 2 ou 3 no quality"() {
        given:
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        11     | 10             | 50      | 50
        10     | 9              | 49      | 50
        5      | 4              | 48      | 50
    }
}
