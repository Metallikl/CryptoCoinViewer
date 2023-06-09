package br.com.dluche.criptocoinviewer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.dluche.criptocoinviewer.data.database.dao.CryptoCoinDao
import br.com.dluche.criptocoinviewer.data.model.entity.CryptoCoinEntity


@Database(entities = [CryptoCoinEntity::class], version = 1)
abstract class CryptoCoinDb: RoomDatabase() {
    abstract fun coinsDao(): CryptoCoinDao
}