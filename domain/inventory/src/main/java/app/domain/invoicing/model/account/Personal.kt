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
data class Personal(
    @PrimaryKey
    val nif: String,
    @Embedded
    val direction: Address,
    @ColumnInfo(name= "id_Account")
    val idAccount: Int,
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Personal

        return nif == other.nif
    }

    override fun hashCode(): Int {
        return nif.hashCode()
    }
}