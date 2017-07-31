package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			if (!items[i].name.equals("Aged Brie")
					&& !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				if (items[i].quality > 0) {
					if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
						items[i].quality = items[i].quality - 1;
					}
				}
			} else {
				if (items[i].quality < 50) {
					items[i].quality = items[i].quality + 1;

					if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].sellIn < 11) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}

						if (items[i].sellIn < 6) {
							if (items[i].quality < 50) {
								items[i].quality = items[i].quality + 1;
							}
						}
					}
				}
			}

			if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
				items[i].sellIn = items[i].sellIn - 1;
			}

			if (items[i].sellIn < 0) {
				if (!items[i].name.equals("Aged Brie")) {
					if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
						if (items[i].quality > 0) {
							if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
								items[i].quality = items[i].quality - 1;
							}
						}
					} else {
						items[i].quality = items[i].quality - items[i].quality;
					}
				} else {
					if (items[i].quality < 50) {
						items[i].quality = items[i].quality + 1;
					}
				}
			}
		}
	}

	/**
	 * Pretty simple, right? Well this is where it gets interesting:
	 * 
	 * - Once the sell by date has passed, Quality degrades twice as fast - The
	 * Quality of an item is never negative - "Aged Brie" actually increases in
	 * Quality the older it gets - The Quality of an item is never more than 50
	 * - "Sulfuras", being a legendary item, never has to be sold or decreases
	 * in Quality - "Backstage passes", like aged brie, increases in Quality as
	 * its SellIn value approaches; Quality increases by 2 when there are 10
	 * days or less and by 3 when there are 5 days or less but Quality drops to
	 * 0 after the concert
	 * 
	 * We have recently signed a supplier of conjured items. This requires an
	 * update to our system:
	 * 
	 * - "Conjured" items degrade in Quality twice as fast as normal items
	 * 
	 */
	public void updateQualityV2(int day) {
		// loop all items and apply rules
		for (Item item : items) {
			applyRules(item, day);
		}
	}

	/**
	 * @param item
	 * @param day
	 */
	private void applyRules(Item item, int day) {

		if (isExcluded(item)) {
			return;
		} else {
			item.sellIn -= 1;
			if (item.name.equals("Aged Brie")) {
				increaseQuality(item, 1);
			} else if (item.name.contains("Backstage passes")) {
				applyTicketRule(item, day);
			} else {
				if ((item.sellIn < 0) || (item.name.contains("Conjured")) ) { // Once the sell by date has passed
					decreaseQuality(item, 2); // quality degrades twice as fast
				} else {
					decreaseQuality(item, 1); // quality degrades
				}
			}
		}
	}

	/**
	 * "Sulfuras", being a legendary item, never has to be sold or decreases in
	 * Quality
	 * 
	 * @param item
	 * @return
	 */
	private boolean isExcluded(Item item) {
		if (item.name.contains("Sulfuras")) {
			return true;
		}
		return false;
	}

	/**
	 * Quality of an item is never negative
	 * 
	 * @param item
	 */
	private void decreaseQuality(Item item, int value) {
		if ((item.quality - value) >= 0) {
			item.quality -= value;
		} else {
			item.quality = 0;
		}
	}

	/**
	 * The Quality of an item is never more than 50
	 * 
	 * @param item
	 */
	private void increaseQuality(Item item, int value) {
		if ((item.quality + value) < 50) {
			item.quality += value;
		} else {
			item.quality = 50; // max value
		}
	}

	/**
	 * "Backstage passes", like aged brie, increases in Quality as its SellIn
	 * value approaches; Quality increases by 2 when there are 10 days or less
	 * and by 3 when there are 5 days or less but Quality drops to 0 after the
	 * concert
	 */
	private void applyTicketRule(Item item, int day) {
		int remainDays = item.sellIn - day;

		if (remainDays > 10) { // more than 10 days validity
			increaseQuality(item, 1);
		} else if (remainDays > 5 && remainDays <= 10) { // validity between ]5,
															// 10]
			increaseQuality(item, 2);
		} else if (remainDays <= 5) { // validity <= 5
			increaseQuality(item, 3);
		} else { // passed
			item.quality = 0;
		}
	}

}