package ru.lforb.mybooks.whattosee.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.lforb.mybooks.whattosee.Repository.Repository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory @Inject constructor(val repository: Repository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}




