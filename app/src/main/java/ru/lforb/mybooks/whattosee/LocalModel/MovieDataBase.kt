package ru.lforb.mybooks.whattosee.LocalModel

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lforb.mybooks.whattosee.RemoteModel.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun myMovieDao() : MyMovieDao
}