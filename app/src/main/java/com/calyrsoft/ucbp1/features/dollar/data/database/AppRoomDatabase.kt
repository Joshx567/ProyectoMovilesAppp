package com.calyrsoft.ucbp1.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calyrsoft.ucbp1.features.dollar.data.database.dao.IDollarDao
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.movie.data.database.dao.IMovieDao
import com.calyrsoft.ucbp1.features.movie.data.database.entity.MovieEntity

@Database(
    entities = [DollarEntity::class, MovieEntity::class], // <-- agregamos MovieEntity
    version = 1,
    exportSchema = false // evita advertencias de schema
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun dollarDao(): IDollarDao
    abstract fun movieDao(): IMovieDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppRoomDatabase::class.java, "app_database") // puedes unificar nombre
                    .fallbackToDestructiveMigration() // para pruebas rápidas si cambias entidades
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
