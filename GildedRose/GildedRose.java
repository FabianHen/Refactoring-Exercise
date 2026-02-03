package gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static Item[] items;

    public static void updateQuality() {
        for (Item item : items) {

            if(item.name.equals(SULFURAS)) {
                continue;
            }

            if(item.name.equals(BACKSTAGE) || item.name.equals(AGED_BRIE)) {
                if (item.quality < 50) {
                    item.quality++;

                    if (item.name.equals(BACKSTAGE)) {
                        if (item.sellIn < 11 && item.quality < 50) {
                            item.quality++;
                        }

                        if (item.sellIn < 6 && item.quality < 50) {
                            item.quality++;
                        }
                    }
                }
            } else if(item.quality > 0){
                item.quality--;
            }

            item.sellIn--;

            if (item.sellIn < 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < 50) {
                        item.quality++;
                    }
                } else if (item.name.equals(BACKSTAGE)) {
                    item.quality = 0;
                } else if (item.quality > 0) {
                    item.quality--;
                }
            }
        }
    }
}
