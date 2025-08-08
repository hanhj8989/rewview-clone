package kr.co.rewview.clone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kr.co.rewview.clone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        setupNavigation()
    }
    
    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Toolbar 타이틀 변경
            when (destination.id) {
                R.id.navigation_campaign -> {
                    binding.tvTitle.text = getString(R.string.campaign_kr)
                }
                R.id.navigation_review -> {
                    binding.tvTitle.text = getString(R.string.review_kr)
                }
                R.id.navigation_magazine -> {
                    binding.tvTitle.text = getString(R.string.magazine_kr)
                }
                R.id.navigation_rewview_store -> {
                    binding.tvTitle.text = getString(R.string.rewview_store)
                }
                R.id.navigation_mypage -> {
                    binding.tvTitle.text = getString(R.string.mypage_kr)
                }
            }

            when (destination.id) {
                R.id.navigation_campaign, R.id.navigation_review, R.id.navigation_magazine, R.id.navigation_rewview_store -> {
                    showToolbarMainMenu()
                    hideToolbarMyPageMenu()
                    //뱃지는 나중에...
                    // viewModel.userNotificationInfoV2.value?.let {
                    //     notificationBadge(it)
                    // }
                }
                R.id.navigation_mypage -> {
                    showToolbarMyPageMenu()
                    hideToolbarMainMenu()
                }
            }

            when (destination.id) {
                R.id.navigation_campaign -> binding.navView.menu.findItem(R.id.navigation_campaign)?.isChecked = true
                R.id.navigation_review -> binding.navView.menu.findItem(R.id.navigation_review)?.isChecked = true
                R.id.navigation_magazine -> binding.navView.menu.findItem(R.id.navigation_magazine)?.isChecked = true
                R.id.navigation_rewview_store -> binding.navView.menu.findItem(R.id.navigation_rewview_store)?.isChecked = true
                R.id.navigation_mypage -> binding.navView.menu.findItem(R.id.navigation_mypage)?.isChecked = true
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_campaign, R.id.navigation_review, R.id.navigation_magazine, R.id.navigation_rewview_store, R.id.navigation_mypage
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        
        binding.navView.setupWithNavController(navController)
    }
    
    private fun showToolbarMainMenu() {
        binding.notificationIcon.visibility = View.VISIBLE
    }
    
    private fun hideToolbarMainMenu() {
        binding.notificationIcon.visibility = View.GONE
    }
    
    private fun showToolbarMyPageMenu() {
        binding.settingsIcon.visibility = View.VISIBLE
    }
    
    private fun hideToolbarMyPageMenu() {
        binding.settingsIcon.visibility = View.GONE
    }
}