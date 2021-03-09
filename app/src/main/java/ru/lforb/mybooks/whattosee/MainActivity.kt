package ru.lforb.mybooks.whattosee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModel
import ru.lforb.mybooks.whattosee.ViewModel.MovieViewModelFactory
import ru.lforb.mybooks.whattosee.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var viewModel:MovieViewModel
    @Inject lateinit var factory:MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        navController = Navigation.findNavController(this, R.id.navHost)
        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController,binding.drawerLayout)
        binding.navView.setupWithNavController(navController)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        if(viewModel.favoriteMovie.isEmpty()){
            viewModel.getAllMovies()
        }
        if(viewModel.favoriteTv.isEmpty()){
            viewModel.getAllTv()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
       return navController.popBackStack() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item)
    }
}