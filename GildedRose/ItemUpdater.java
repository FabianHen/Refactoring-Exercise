package gildedrose;

public abstract class ItemUpdater {
    /**
     * Updates the quality and sellIn values of the given item according to its specific rules.
     *
     * @param item the item to be updated
     */
    abstract void update(Item item);

    /**
     * Increases the quality of the given item by 1, ensuring it does not exceed 50.
     *
     * @param item the item whose quality is to be increased
     */
    protected void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality ++;
        }
    }

    /**
     * Decreases the quality of the given item by 1, ensuring it does not go below 0.
     *
     * @param item the item whose quality is to be decreased
     */
    protected void decreaseQuality(Item item) {
       if (item.quality > 0) {
           item.quality --;
       }
    }
}
