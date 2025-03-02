package com.example.login.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.login.data.model.Account
import com.example.login.data.model.Business
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(business: Business)

    @Update
    suspend fun update(business: Business)

    @Query("SELECT * FROM Business ORDER BY cif ASC ")
    fun getAll(): Flow<List<Business>>

    @Delete
    suspend fun delete(business: Business)

}