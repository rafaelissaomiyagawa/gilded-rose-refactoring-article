package com.gildedrose

import spock.lang.Specification

class CommonItemSpecification extends Specification {
    def "No final do dia, sellIn e quality deve decrementar em 1"() {
        given:
        Item item = new Item("Common", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        10     | 9              | 10      | 9
        9      | 8              | 8       | 7
        1      | 0              | 1       | 0
    }

    def "Se sellIn for menor que zero, quality deve diminuir duas vezes mais rapido"() {
        given:
        Item item = new Item("Common", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        0      | -1             | 10      | 8
        -1     | -2             | 8       | 6
        -10    | -11            | 2       | 0
    }

    def "Quality não pode ser negativa"() {
        given:
        Item item = new Item("Common", sellIn, quality)
        Item[] items = [item]

        when:
        new GildedRose(items).updateQuality()

        then:
        item.sellIn == expectedSellIn
        item.quality == expectedQuality

        where:
        sellIn | expectedSellIn | quality | expectedQuality
        // Casos onde quality decrementa 1
        5      | 4              | 0       | 0
        1      | 0              | 0       | 0

        // Casos onde quality diminui duas vezes mais rápido
        0      | -1             | 1       | 0
        -5     | -6             | 1       | 0
        0      | -1             | 0       | 0
        -5     | -6             | 0       | 0
    }
}