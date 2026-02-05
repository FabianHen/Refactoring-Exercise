package gildedrose;

/**
 * The GildedRose class manages an inventory of items and updates their quality and sellIn values according to specific rules.
 */
class GildedRose {

    /**
     * The inventory of items in the Gilded Rose.
     */
    public static Item[] items;

    /**
     * Updates the quality and sellIn values of all items in the inventory.
     */
    public static void updateQuality() {
        for (Item item : items) {
            ItemUpdaterFactory.forItem(item).update(item);
        }
    }
}
