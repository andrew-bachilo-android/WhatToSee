package ru.lforb.mybooks.whattosee.UI

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import ru.lforb.mybooks.whattosee.MainActivity
import ru.lforb.mybooks.whattosee.R
import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModel
import java.lang.StringBuilder

class GenresDialogFragment : DialogFragment() {
    lateinit var viewModel: MovieViewModel


    private val genresBuilder = StringBuilder()
    private val checkedItems = booleanArrayOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity as MainActivity).get(MovieViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val stringArray = resources.getStringArray(R.array.genres)
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Выберите жанр")
                .setMultiChoiceItems(stringArray, checkedItems) {
                        dialog, which, isChecked ->
                    checkedItems[which] = isChecked
                }
                .setPositiveButton("Готово"
                ) { dialog, id ->
                    if(viewModel.flag){
                        for (i in stringArray.indices) {
                            val checked = checkedItems[i]
                            if (checked) {
                                viewModel.genre.add(
                                    when(i){
                                        0 ->  "28"
                                        1 ->  "12"
                                        2 ->   "16"
                                        3 ->   "35"
                                        4 ->   "80"
                                        5 ->   "99"
                                        6 ->   "18"
                                        7 ->   "10751"
                                        8 ->   "14"
                                        9 ->   "36"
                                        10 ->  "27"
                                        11 ->   "10402"
                                        12 ->   "9648"
                                        13 ->   "10749"
                                        14 ->   "53"
                                        15 ->   "10752"
                                        16 ->  "37"
                                        else -> "35"
                                    }
                                )
                                genresBuilder.append(stringArray[i] + "  ")
                            }
                        }
                        viewModel.genreChoice.postValue(genresBuilder.toString())
                        Log.d("flag", viewModel.genre.toString())
                    }else{
                        for (i in stringArray.indices) {
                            val checked = checkedItems[i]
                            if (checked) {
                                viewModel.genreEx.add(
                                    when(i){
                                        0 ->  "28"
                                        1 ->  "12"
                                        2 ->   "16"
                                        3 ->   "35"
                                        4 ->   "80"
                                        5 ->   "99"
                                        6 ->   "18"
                                        7 ->   "10751"
                                        8 ->   "14"
                                        9 ->   "36"
                                        10 ->  "27"
                                        11 ->   "10402"
                                        12 ->   "9648"
                                        13 ->   "10749"
                                        14 ->   "53"
                                        15 ->   "10752"
                                        16 ->  "37"
                                        else -> "35"
                                    }
                                )
                                genresBuilder.append(stringArray[i] + "  ")
                            }
                        }
                        viewModel.genreChoiceEx.postValue(genresBuilder.toString())
                    }
                }
                .setNegativeButton("Отмена") {
                        dialog, _ ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

