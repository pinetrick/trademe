package com.pine.mytrademetest.main_listings.discover

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderDiscover(var v: View): RecyclerView.ViewHolder(v){

    var text: TextView? = null;
    var ret: TextView? = null;


    init{
        //text = v.findViewById(R.id.text);
    }

}