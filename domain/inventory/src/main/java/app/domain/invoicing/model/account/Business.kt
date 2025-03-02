package com.example.login.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["id_Account"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)

data class Business(
    @PrimaryKey
    val id: Int,
    val cif: String,
    val nameCompany: String,
    @Embedded //Los campos de la clase Address se crean en la tabla Bussiness
    val direction: Address,
    @ColumnInfo(name= "id_Account")
    val idAccount: Int, //Foreign key reference Account
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Business

        return cif == other.cif
    }

    override fun hashCode(): Int {
        return cif.hashCode()
    }
}