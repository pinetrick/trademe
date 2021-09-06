package com.pine.mytrademetest.main_listings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pine.mytrademetest.R
import kotlinx.android.synthetic.main.main_listing_activity.*


class ActivityMainListings : AppCompatActivity() {
    private var lastIndex = 0
    private var mFragments = mutableListOf<Fragment>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_listing_activity)

        initFragementData();
        initBottomNavigation();
    }

    private fun initFragementData() {
        mFragments = ArrayList()
        mFragments.add(FragmentSearch())
        mFragments.add(FragmentCart())
        mFragments.add(FragmentProfile())
        // Init the fragement to search
        switchToSearchFragment(0)
    }

    //Call once during init
    private fun initBottomNavigation() {
        main_listing_nav.setOnItemReselectedListener {
            when(it.itemId){
                R.id.main_listing_menu_search -> switchToSearchFragment(0);
                R.id.main_listing_menu_cart -> switchToSearchFragment(1);
                R.id.main_listing_menu_profile -> switchToSearchFragment(2);
            }
        }

    }

    //Switch UI to Specify Fragment
    private fun switchToSearchFragment(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.ll_frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}