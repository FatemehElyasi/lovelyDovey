package ir.fatemelyasi.lovely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import ir.fatemelyasi.lovely.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //--------------------------------------------------------------------------

        toolbar = findViewById(R.id.mytoolbar)
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawer
        navigationView = binding.navigationViewMain

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.chartFragment,
                R.id.storyFragment,
                R.id.blankFragment,
                R.id.blankFragment2
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)
    //--------------------------------------------------------------------------
    }

    //--------------------------------------------------------------------------
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    //--------------------------------------------------------------------------

}

//add firebase
//add animate