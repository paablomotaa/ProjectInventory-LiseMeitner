package com.example.inventory.inject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import app.domain.invoicing.InventoryDataBase
import app.domain.invoicing.dao.InventoryDao
import app.domain.invoicing.dao.InventoryProductsDao
import app.domain.invoicing.dao.ProductDao
import app.domain.invoicing.model.inventoryproducts.InventoryProducts
import app.domain.invoicing.repositoryDB.InventoryProductsRepositoryDB
import app.domain.invoicing.repositoryDB.InventoryRepositoryDB
import com.example.inventory.home.NavigationDrawerItemSealed
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepository
    }
    */


    /**
     * Método que provee el DataStore (api-valor) de la sessión
     */
    @Singleton
    @Provides
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile(Session.DATA) })
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(inventoryDao:InventoryDao):InventoryRepositoryDB{
        return InventoryRepositoryDB(inventoryDao)
    }

    @Provides
    @Singleton
    fun provideInventoryDataBase(@ApplicationContext context: Context): InventoryDataBase {
        return InventoryDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideProductDao(inventoryDataBase: InventoryDataBase): ProductDao{
        return inventoryDataBase.getProductDao()
    }
    @Provides
    @Singleton
    fun provideInventoryDao(inventoryDatabase:InventoryDataBase):InventoryDao{
        return inventoryDatabase.getInventoryDao()
    }
    @Provides
    @Singleton
    fun provideInventoryProductsDao(inventoryDatabase: InventoryDataBase):InventoryProductsDao{
        return inventoryDatabase.getInventoryProductsDao()
    }
    @Provides
    @Singleton
    fun provideInventoryProductsRepository(inventoryProductsDao:InventoryProductsDao):InventoryProductsRepositoryDB{
        return InventoryProductsRepositoryDB(inventoryProductsDao)
    }
}