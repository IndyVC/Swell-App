package com.example.swell

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.swell.databinding.ActivityMainBinding
import info.androidhive.fontawesome.FontDrawable

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search_spot, R.id.navigation_current_location, R.id.navigation_favourites, R.id.navigation_top_location
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        initBottomNavigation()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }

    /**
     * Changing navigation drawer icons
     * This involves looping through menu items and applying icons
     */
    fun initBottomNavigation() {
        val navigationView = binding.navView
        val icons = intArrayOf(
            R.string.fa_search_solid,R.string.fa_map_marked_alt_solid,R.string.fa_heart_solid,R.string.fa_star_solid
        );

        renderMenuIcons(navigationView.menu, icons, true, false)
    }

    /**
     * Looping through menu icons are applying font drawable
     */
    fun renderMenuIcons(menu: Menu, icons:IntArray, isSolid: Boolean, isBrand: Boolean) {

        var i: Int = 0
        while (i < menu.size()) {
            val menuItem: MenuItem = menu.getItem(i)

            if (!menuItem.hasSubMenu()) {
                val drawable: FontDrawable = FontDrawable(this, icons[i], isSolid, isBrand)
                drawable.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                drawable.setTextSize(22.toFloat())
                menu.getItem(i).setIcon(drawable)
            }
            i++
        }
    }
}
