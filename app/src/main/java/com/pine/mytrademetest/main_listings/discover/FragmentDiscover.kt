package com.pine.mytrademetest.main_listings.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pine.mytrademetest.R
import com.pine.mytrademetest.a
import com.pine.mytrademetest.app
import com.pine.mytrademetest.n
import com.pine.mytrademetest.net.beans.ListingItemBaseBean
import com.pine.mytrademetest.net.beans.ListingItemBean
import com.pine.mytrademetest.net.require


class FragmentDiscover : Fragment() {

    var lists = ArrayList<ListingItemBean>();
    var searchListAdapter = ViewAdapterDiscover(this);

    lateinit var search_list: RecyclerView;
    lateinit var search_btn: ImageView;
    lateinit var cart_btn: ImageView;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = initViews(inflater, container);
        initSearchList();
        initBtnClicks();
        reloadLatest()

        return view;
    }

    private fun initViews(inflater: LayoutInflater, container: ViewGroup?): View? {
        var view = inflater.inflate(R.layout.main_listing_discover, container, false)
        search_list = view.findViewById(R.id.search_list)
        search_btn = view.findViewById(R.id.search_btn)
        cart_btn = view.findViewById(R.id.cart_btn)


        return view;
    }

    private fun initBtnClicks() {
        search_btn.setOnClickListener { Toast.makeText(a(), "Placeholder: Search Clicked", Toast.LENGTH_LONG).show() }
        cart_btn.setOnClickListener { Toast.makeText(a(), "Placeholder: Cart Clicked", Toast.LENGTH_LONG).show() }
    }

    private fun initSearchList() {
        var layoutManager = LinearLayoutManager(app())

        search_list.setLayoutManager(layoutManager)
        search_list.adapter = searchListAdapter;


    }

    private fun reloadLatest(){
        n().latest().require {
            it.let {
                lists = it!!.List;

                searchListAdapter.notifyDataSetChanged()
            }
        }
    }
}