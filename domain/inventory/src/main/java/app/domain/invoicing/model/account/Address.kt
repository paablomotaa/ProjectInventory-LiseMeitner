package com.example.login.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address constructor(
    @PrimaryKey
    val idAddress: Int,
    val street: String,
    val city: String,
    @ColumnInfo(name = "postal_code")
    val postalCode: Int,
    val country: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Address

        if (street != other.street) return false
        if (city != other.city) return false
        if (country != other.country) return false

        return true
    }

    override fun hashCode(): Int {
        var result = street.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + country.hashCode()
        return result
    }
}