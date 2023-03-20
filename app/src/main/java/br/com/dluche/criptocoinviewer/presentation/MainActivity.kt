package br.com.dluche.criptocoinviewer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.dluche.criptocoinviewer.R
import br.com.dluche.criptocoinviewer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHost: NavHostFragment
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNav()
    }

    private fun initNav() {
        navHost =
            supportFragmentManager.findFragmentById(R.id.crypto_coin_nav_host) as NavHostFragment
        navController = navHost.navController
    }
}