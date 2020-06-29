package com.example.exploretheworld.flow.host

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.exploretheworld.R
import kotlinx.android.synthetic.main.activity_host.*
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle

class HostActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.container_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(menuNavigation, navController)
        drawerToggle()
    }


    private fun drawerToggle() {
        val drawerToggle = ActionBarDrawerToggle(
            this,
            host_drawer,
            R.string.navigation_drawer_open,
            R.string.drawer_close
        )
        host_drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (host_drawer.isDrawerOpen(GravityCompat.START)) {
            host_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                host_drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}