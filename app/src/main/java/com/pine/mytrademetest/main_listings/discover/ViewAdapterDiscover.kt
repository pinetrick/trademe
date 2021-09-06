package com.pine.mytrademetest.main_listings.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pine.mytrademetest.App
import com.pine.mytrademetest.R
import com.pine.mytrademetest.app

class ViewAdapterDiscover (var discoverFragment: FragmentDiscover) : RecyclerView.Adapter<ViewHolderDiscover>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDiscover {
        var li = LayoutInflater.from(parent.context).inflate(R.layout.main_listing_discover_adapter, parent, false)
        return ViewHolderDiscover(li);
    }

    override fun getItemCount(): Int {
        return discoverFragment.lists.count()
    }

    override fun onBindViewHolder(holder: ViewHolderDiscover, position: Int, payloads: MutableList<Any> ) {
        onBindViewHolder(holder, position);
    }

    override fun onBindViewHolder(holder: ViewHolderDiscover, position: Int) {




    }





}
