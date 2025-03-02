package com.example.login.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.login.data.model.Business
import com.example.login.data.model.Personal
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(personal: Personal)

    @Update
    suspend fun update(personal: Personal)

    @Query("SELECT * FROM Personal ORDER BY nif ASC ")
    fun getAll(): Flow<List<Personal>>

    @Delete
    suspend fun delete(personal: Personal)
}