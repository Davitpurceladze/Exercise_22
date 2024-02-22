package com.example.exercise_22.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.exercise_22.R
import com.example.exercise_22.databinding.ActivityMainBinding
import com.example.exercise_22.presentation.screen.home.HomeFragmentDirections
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readPushToken()

        bottomNavigationView = binding.bottomNavigationView
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            bottomNavigationView, navController
        )
        navigateToPostDetailsFragment()


        bottomNavigationView.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.favoritesFragment -> {true}
                R.id.homeFragment -> {true}
                R.id.chatFragment -> {true}
                R.id.notificationsFragment -> {true}
                else -> {false}
            }
        }
    }

    private fun navigateToPostDetailsFragment() {
        val postId = intent.getStringExtra("postId")
        postId?.let {
            val navigationToPostDetailsFragment = HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(it.toInt())
            navController.navigate(navigationToPostDetailsFragment)
        }
    }

    private fun readPushToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast

        })
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
        }
    }
}
