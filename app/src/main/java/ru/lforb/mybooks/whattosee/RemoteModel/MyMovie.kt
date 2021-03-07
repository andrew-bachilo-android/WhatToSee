package ru.lforb.mybooks.whattosee.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
//{"adult":false,
//    "backdrop_path":"/aGcAg7wge0xAqJuOTJs6HPNXVVo.jpg",
//    "genre_ids":[35,10751,12],
//    "id":587807,
//    "original_language":"en",
//    "original_title":"Tom & Jerry",
//    "overview":"Кайла, работница престижного отеля, где обитает мышонок Джерри, рискующий нарушить ход дорогой свадьбы. Чтобы разобраться с грызуном, она нанимает уличного кота Тома, но решить проблему не так уж и просто.",
//    "popularity":226.646,
//    "poster_path":"/e06BpqZIxRSpvNSbItcGcgs0S5I.jpg",
//    "release_date":"2021-02-11",
//    "title":"Том и Джерри",
//    "video":false,
//    "vote_average":4,
//    "vote_count":1}



data class MyMovie(
    val page:Int,
    val results:MutableList<Movie>,
    val total_pages:Int,
    val total_results:Int


)
@Entity(tableName = "movies")
data class Movie(
    val first_air_date:String,
    @PrimaryKey val id:Int,
    val name:String,
    val overview:String,
    val poster_path:String,
    val release_date:String,
    val title:String,
    val vote_average:Double,
    val isMovie:Int
)



