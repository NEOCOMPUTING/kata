package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

public class GildedRoseTest {

	int days = 2;
	
	/**
	 * List of items in the store
	 * 
	 * @return
	 */
	private Item[] getItems() {
		Item[] items = new Item[] { 
				new Item("+5 Dexterity Vest", 10, 20), //
				new Item("Aged Brie", 2, 0) ,
				new Item("Elixir of the Mongoose", 5, 7), //
				new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
				new Item("Sulfuras, Hand of Ragnaros", -1, 80),
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
				new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
				new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
				new Item("Conjured Mana Cake", 3, 6) 
				};
		return items;
	}

	/**
	 * This test avoid the comparaison of the last element as the existing
	 * version gives a wrong result for the new element added
	 */
	@Test
	public void beforeAfterTestOk() {

		Item[] beforeItems = getItems();
		Item[] afterItems = getItems();

		
		previousVersion(beforeItems, days);
		newVersion(afterItems, days);

		boolean isIdentical = true;
		for (int i = 0; i < beforeItems.length -1 ; i++) {

			isIdentical &= beforeItems[i].toString().equals(afterItems[i].toString());

			StringBuilder b = new StringBuilder("Before [" + beforeItems[i].toString() + "]\n");
			b.append("After  [" + afterItems[i].toString() + "]  = " + isIdentical + "\n\n");

			System.out.println(b.toString());

			if (!isIdentical) {

				Assert.assertNotEquals(b.toString(), beforeItems[i].toString(), afterItems[i].toString());
				break;
			}
		}

		Assert.assertEquals("All items computed propertly", true, isIdentical);
	}

	/**
	 * This test failed as it compares result of all elements (including element
	 * which failed in the existing version)
	 */
	@Test
	public void beforeAfterTestWithFix() {

		Item[] beforeItems = getItems();
		Item[] afterItems = getItems();

		previousVersion(beforeItems, days);
		newVersion(afterItems, days);

		boolean isIdentical = true;
		for (int i = 0; i < beforeItems.length; i++) {

			isIdentical &= beforeItems[i].toString().equals(afterItems[i].toString());

			StringBuilder b = new StringBuilder("Before [" + beforeItems[i].toString() + "]\n");
			b.append("After  [" + afterItems[i].toString() + "]  = " + isIdentical + "\n\n");

			System.out.println(b.toString());

			if (!isIdentical) {

				Assert.assertNotEquals(b.toString(), beforeItems[i].toString(), afterItems[i].toString());
				break;
			}
		}

		Assert.assertEquals("All items computed propertly", true, !isIdentical);
	}

	public void previousVersion(Item[] items, int days) {
		GildedRose app = new GildedRose(items);
		for (int i = 0; i < days; i++) {
			app.updateQuality();
		}

	}

	public void newVersion(Item[] items, int days) {
		GildedRose app = new GildedRose(items);
		for (int i = 0; i < days; i++) {
			app.updateQualityV2(i);
		}

	}

}
