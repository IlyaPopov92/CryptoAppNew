package com.example.cryptoappnew.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cryptoappnew.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "main.db"

        private val LOCK = Any()

        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                        .addMigrations(MIGRATION_1_2)
                        .build()
                db = instance
                return instance
            }
        }

         val MIGRATION_1_2  = object : Migration (1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE tasks ADD COLUMN deadline LONG")
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

}
