package gildedrose;

/**
 * Factory class for creating appropriate ItemUpdater instances based on the type of item.
 */
public class ItemUpdaterFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";

    /**
    * Returns an ItemUpdater instance that is appropriate for the given item based on its name.
    *
    * @param item the item for which an updater is needed
    * @return an ItemUpdater instance suitable for the given item
    */
    static ItemUpdater forItem(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> new AgedBrieUpdater();
            case BACKSTAGE -> new BackstagePassUpdater();
            case SULFURAS -> new SulfurasUpdater();
            case CONJURED -> new ConjuredItemUpdater();
            default -> new NormalItemUpdater();
        };
    }
}
