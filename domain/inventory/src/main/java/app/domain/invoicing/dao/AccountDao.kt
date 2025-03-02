package com.example.login.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.domain.invoicing.account.Email
import com.example.login.data.model.Account
import kotlinx.coroutines.flow.Flow


/**
 * Account dao
 * Contiene los metodos necesarios para crear, seleccionar, actualizar y eliminar (CRUD)
 * objetos de Account en la base de datos Sqlite
 *
 * @constructor Create empty Account dao
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("SELECT * FROM Account Where id = :accountId ")
    suspend fun getAccountById(accountId: Int): Account?

    @Query("SELECT * FROM Account Where email = :accountEmail")
    suspend fun getAccountByEmail(accountEmail: Email): Account?

    @Query("SELECT * FROM Account ORDER BY name ASC")
    fun getAllAccount(): Flow<List<Account>>

    @Query ("SELECT * from Account where email= :email and password = :password")
    suspend fun validate(email: Email, password: String): Account?
}