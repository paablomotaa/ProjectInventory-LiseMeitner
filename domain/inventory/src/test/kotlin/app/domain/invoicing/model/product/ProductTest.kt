package app.domain.invoicing.model.product

import app.base.utils.Status
import app.domain.invoicing.product.Product
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class ProductTest {

    @Test
    fun `product ID debe ser igual true`() {
        val product1 = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )

        val product2 = product1.copy()
        assertTrue(product1 == product2)
    }

    @Test
    fun `product ID debe ser diferente false`() {
        val product1 = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )

        val product3 = product1.copy(id = 2L)
        assertFalse(product1 == product3)
    }

    @Test
    fun `product hashCode debe basarse en ID`() {
        val product = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )

        assertEquals(product.id.hashCode(), product.hashCode())
    }

    @Test
    fun `product toString debe devolver nombre`() {
        val product = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )

        assertEquals("Laptop", product.toString())
    }

    @Test
    fun `equals debe devolver true cuando se compara con el mismo objeto`() {
        val product = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        assertTrue(product.equals(product))
    }

    @Test
    fun `equals debe devolver true cuando los ids son iguales`() {
        val product1 = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        val product2 = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        assertTrue(product1.equals(product2))
    }

    @Test
    fun `equals debe devolver false cuando los ids son diferentes`() {
        val product1 = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        val product2 = Product(
            id = 2,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        assertFalse(product1.equals(product2))
    }

    @Test
    fun `equals debe devolver false cuando se compara con null`() {
        val product = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        assertFalse(product.equals(null))
    }

    @Test
    fun `equals debe devolver false cuando se compara con otro tipo de objeto`() {
        val product = Product(
            id = 1L,
            code = "P123",
            name = "Laptop",
            shortName = "LT",
            description = "High-end laptop",
            numSerial = 123456789.0,
            codModel = "LT123",
            typeProduct = "Electronics",
            category = "Computers",
            section = "IT",
            status = Status.NEW,
            amount = 10,
            price = 1500.0,
            image = "image_url",
            acquisitionDate = LocalDate.of(2023, 1, 1),
            cancellationDate = LocalDate.of(2025, 1, 1),
            notes = "Some notes",
            tags = "Tech"
        )
        val notAProduct = "No soy un producto"
        assertFalse(product.equals(notAProduct))
    }
}