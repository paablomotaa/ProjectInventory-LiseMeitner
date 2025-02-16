package app.domain.invoicing.model.inventory

import app.base.utils.Status
import app.domain.invoicing.inventory.Inventory
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate
import java.util.Date

class InventoryTest {

    @Test
    fun testEqualsSameObject() {
        val inventory = Inventory(1, "A001", "Item1", "I1", "Description", "Type", Date(), Date(), Date())
        assertTrue(inventory == inventory)
    }


    @Test
    fun testEqualsDifferentId() {
        val date = Date()
        val inventory1 = Inventory(1, "A001", "Item1", "I1", "Description", "Type", date, date, date)
        val inventory2 = Inventory(2, "A001", "Item1", "I1", "Description", "Type", date, date, date)
        assertFalse(inventory1 == inventory2)
    }

    @Test
    fun testEqualsDifferentCode() {
        val date = Date()
        val inventory1 = Inventory(1, "A001", "Item1", "I1", "Description", "Type", date, date, date)
        val inventory2 = Inventory(1, "A002", "Item1", "I1", "Description", "Type", date, date, date)
        assertFalse(inventory1 == inventory2)
    }

    @Test
    fun testEqualsNullObject() {
        val inventory = Inventory(1, "A001", "Item1", "I1", "Description", "Type", Date(), Date(), Date())
        assertFalse(inventory.equals(null))
    }

    @Test
    fun testEqualsDifferentClass() {
        val inventory = Inventory(1, "A001", "Item1", "I1", "Description", "Type", Date(), Date(), Date())
        val differentObject = "No soy un inventario"
        assertFalse(inventory.equals(differentObject))
    }
    @Test
    fun toStringReturnName(){
        val inventory = Inventory(1, "A001", "Item1", "I1", "Description", "Type", Date(), Date(), Date())
        assertTrue("Item1".equals(inventory.toString()))
    }
    @Test
    fun toStringReturnNameFalse(){
        val inventory = Inventory(1, "A001", "Item1", "I1", "Description", "Type", Date(), Date(), Date())
        assertFalse("Item2".equals(inventory.toString()))
    }
}