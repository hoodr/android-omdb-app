package com.example.walmartinhomedeliverytakehome

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupWithNavController


import com.example.walmartinhomedeliverytakehome.util.addFragment
import com.example.walmartinhomedeliverytakehome.util.replaceFragment
import com.example.walmartinhomedeliverytakehome.view.fragments.EpisodeDetailFragment
import com.example.walmartinhomedeliverytakehome.view.fragments.EpisodeListViewFragment
import com.example.walmartinhomedeliverytakehome.view.fragments.ShowsFragment

// Import the shared view model for home fragments
import com.example.walmartinhomedeliverytakehome.viewmodel.HomeSharedViewModel

/**
 * The view for the Main Activity
 *
 *
 * NOTES:
 *  AppCompatActivity is a subclass of FragmentActivity
 *
 * TODO: Move this into views
 */
class MainActivity : AppCompatActivity() {

    private lateinit var homeSharedViewModel: HomeSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeSharedViewModel =
            ViewModelProviders.of(this).get(HomeSharedViewModel::class.java)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        // Set up top bar
        setSupportActionBar(findViewById(R.id.toolbar))
        subscribeToData()
        addInitialHomeFragment()
        // checkPermissions()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navController = findNavController(R.id.nav_host_fragment)
        setupNavigationMenu(navController)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    private fun addInitialHomeFragment() {
        addFragment(ShowsFragment.newInstance(), R.id.navigation_home)
    }

    /*
    private fun checkPermissions() {
    // Here, thisActivity is the current activity
    if (ContextCompat.checkSelfPermission(
            thisActivity,
            Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                Manifest.permission.android.permission.INTERNET)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(thisActivity,
                arrayOf(Manifest.permission.READ_CONTACTS),
                MY_PERMISSIONS_REQUEST_READ_CONTACTS)

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }
    */

    private fun subscribeToData() {
        homeSharedViewModel.currFragment.observe(this, Observer {
            it?.let {
                Log.d(TAG, it)
                updateHomeFragment(it)
            }
        })
    }

    private fun updateHomeFragment(fragmentName: String) {
        val fragment: Fragment
        when (fragmentName) {
            "ShowsFragment" ->
                fragment = ShowsFragment.newInstance()
            "EpisodeListViewFragment" ->
                fragment = EpisodeListViewFragment.newInstance(1)
            "EpisodeDetailFragment" ->
                fragment = EpisodeDetailFragment.newInstance()
            else ->
                fragment = ShowsFragment.newInstance()
        }

        addFragment(fragment, R.id.navigation_home)
    }

    companion object {
        const val TAG: String = "MainActivity"
    }
}
