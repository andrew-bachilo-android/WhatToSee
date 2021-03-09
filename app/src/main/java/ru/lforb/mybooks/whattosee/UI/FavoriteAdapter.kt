package ru.lforb.mybooks.whattosee.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import ru.lforb.mybooks.whattosee.R
import ru.lforb.mybooks.whattosee.RemoteModel.Movie
import ru.lforb.mybooks.whattosee.RemoteModel.MyMovie

class FavoriteAdapter (val list: MutableList<Movie>):RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val photo = itemView.findViewById<ImageView>(R.id.fav_poster)
        val title = itemView.findViewById<TextView>(R.id.title_fav)
        val rating = itemView.findViewById<TextView>(R.id.rating_fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        val holder = ViewHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val URL_IMG = "https://image.tmdb.org/t/p/w780"
        Picasso.get ()
            .load (URL_IMG.plus(list[position].poster_path))
            .placeholder (R.drawable.movie)
            .error (R.drawable.error_movie)
            .into (holder.photo)
        if(list[position].name == "name"){
            holder.title.text = list[position].title
        }else{
            holder.title.text = list[position].name
        }
        holder.rating.text = list[position].vote_average.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}