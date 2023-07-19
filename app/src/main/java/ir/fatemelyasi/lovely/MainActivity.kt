package ir.fatemelyasi.lovely

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import ir.fatemelyasi.lovely.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var bottomNavigationView: BottomNavigationView


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
        //--------------------------------------------------------------------------ActionBarDrawerToggle
//
//        val actionBarDrawerToggle = ActionBarDrawerToggle(
//            this,
//            binding.drawer,
//            toolbar,
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        binding.drawer.addDrawerListener(actionBarDrawerToggle)
//        actionBarDrawerToggle.syncState()
////         click item in drawer
//        binding.navigationViewMain.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_Anniversary -> {
//
//                }
//                R.id.menu_Setting -> {
//                    binding.drawer.closeDrawer(GravityCompat.START)
//                }
//                R.id.menu_DarkMood -> {
//
//                }
//                R.id.menu_language -> {
//
//                }
//            }
//            true
//        }

    //--------------------------------------------------------------------------bottomNavigationView
        binding.bottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.menu_story -> {

                }
                R.id.menu_home -> {

                }
                R.id.menu_chart -> {

                }
            }
            true
        }

    //-------------------------------------------------------------------------
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