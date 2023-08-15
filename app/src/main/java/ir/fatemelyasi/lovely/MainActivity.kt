package ir.fatemelyasi.lovely

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import ir.fatemelyasi.lovely.databinding.ActivityMainBinding
import ir.fatemelyasi.lovely.databinding.FragmentBlank2Binding
import ir.fatemelyasi.lovely.databinding.FragmentBlankBinding
import ir.fatemelyasi.lovely.fragments.BlankFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--------------------------------------------------------------------------

        navController = findNavController(R.id.fragmentContainerView)

        //Toolbar
        toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(toolbar)
        toolbar.title = "Lovely"
        toolbar.background = null


        //-------------------------------------------------

        //Bottom Navigation
        binding.bottomNav.setupWithNavController(navController)

        //-------------------------------------------------

        //drawer layout
        navigationView = binding.navigationViewMain


//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.storyFragment,
//                R.id.mainFragment,
//                R.id.chartFragment,
//            ), binding.drawer
//        )

//        navigationView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)
        // and onSupportNavigateUp

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawer,
            toolbar,
            R.string.OpenDrawer,
            R.string.CloseDrawer
        )
        binding.drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setCheckedItem(R.id.blankFragment)

        //click item in drawer
        binding.navigationViewMain.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.blankFragment -> {
                }
                R.id.blankFragment2 -> {
                    binding.drawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        //--------------------------------------------------------------------------bottomNavigationView
//        binding.bottomNav.setOnItemReselectedListener {
//            when (it.itemId) {
//                R.id.StoryFragment ->{}
//                R.id.MainFragment -> {}
//                R.id.ChartFragment -> {}
//                else -> {
//                }
//
//            }
//
//        }

        //-------------------------------------------------------------------------
    }

    //--------------------------------------------------------------------------
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainerView, fragment)
        fragmentTransition.commit()
    }

    //for bottom navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainerView).navigateUp(appBarConfiguration)

    }


    //--------------------------------------------------------------------------

}

//add firebase
//add animate