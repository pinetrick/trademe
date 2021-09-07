package com.pine.mytrademetest.main_listings.discover

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.pine.mytrademetest.R

class ViewHolderDiscover(var v: View): RecyclerView.ViewHolder(v){

    var reserved_price: TextView? = null;
    var is_reserved: TextView? = null;
    var buy_now_price: TextView? = null;
    var buy_now: TextView? = null;
    var title: TextView? = null;
    var region: TextView? = null;
    var picture: SimpleDraweeView? = null;
    var listing_line: ConstraintLayout? = null;


    init{
        reserved_price = v.findViewById(R.id.reserved_price);
        is_reserved = v.findViewById(R.id.is_reserved);
        buy_now_price = v.findViewById(R.id.buy_now_price);
        buy_now = v.findViewById(R.id.buy_now);
        title = v.findViewById(R.id.title);
        region = v.findViewById(R.id.region);
        picture = v.findViewById(R.id.picture);
        listing_line = v.findViewById(R.id.listing_line);
    }

}