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

//data class Tv(
//    val id:Int,
//    val name:String,
//    val overview:String,
//    val poster_path:String,
//    val vote_average:Double
//)
//
//
//data class MyTv(
//    val page:Int,
//    val results:MutableList<Tv>,
//    val total_pages:Int,
//    val total_results:Int
//
//
//)
//{"backdrop_path":"/1s5LAbi8udSIIc5AghjwuKfy87i.jpg",
//    "first_air_date":"2021-01-10",
//    "genre_ids":[16,35,18],
//    "id":110070,
//    "name":"Хоримия",
//    "origin_country":["JP"],
//    "original_language":"ja",
//    "original_name":"ホリミヤ",
//    "overview":"На первый взгляд может показаться, что Хори — обыкновенная девушка-подросток, но внешность бывает обманчива. В то время, как родители трудятся на работе, героине остаются все хлопоты по дому, а также забота о младшем брате. Из-за постоянной занятости у неё просто не остаётся времени на то, чтобы насладиться прелестями подростковой жизни. Вскоре она знакомится с Миямурой, который также не показывает в школе настоящего себя. Он кажется недружелюбным тихоней, но за пределами стен школы оказывается абсолютно другим человеком. Так, у этой парочки находится много общего, и постепенно они начинают сближаться.",
////    "popularity":76.484,
//    "poster_path":"/6AZTMdxKKLQcoVQJAQcZgNdanmP.jpg",
//    "vote_average":9.4,
//    "vote_count":4}

