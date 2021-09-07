package com.pine.mytrademetest.main_listings.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pine.mytrademetest.App
import com.pine.mytrademetest.R
import com.pine.mytrademetest.app
import com.pine.mytrademetest.funs.toMoney

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
        //get each list data
        var list = discoverFragment.lists[position]

        holder.picture!!.setImageURI(list.PictureHref)
        holder.title!!.setText(list.Title)
        holder.region!!.setText(list.Region)

        if (list.BuyNowPrice <= 0){
            holder.buy_now_price!!.visibility = View.GONE
            holder.buy_now!!.visibility = View.GONE
        }
        else{
            holder.buy_now_price!!.visibility = View.VISIBLE
            holder.buy_now!!.visibility = View.VISIBLE
            holder.buy_now_price!!.setText(list.BuyNowPrice.toMoney())
        }

        if (list.ReserveState == -1) {
            if (list.IsBuyNowOnly){
                list.ReserveState = 3;
            }
            else if ((list.StartPrice > 0) && (list.BuyNowPrice <= 0)){
                list.ReserveState = 2;
            }
        }
        if (list.ReserveState == 0){//None There is no reserve on the item (i.e. the reserve price is the same as the starting price).
            holder.is_reserved!!.visibility = View.GONE
            holder.reserved_price!!.visibility = View.GONE
        }
        else if (list.ReserveState == 1){//Met The value of the highest bid has exceeded the reserve price.
            holder.is_reserved!!.visibility = View.VISIBLE
            holder.reserved_price!!.visibility = View.VISIBLE

            holder.is_reserved!!.setText(R.string.main_listing_discover_reserve_met)
            holder.reserved_price!!.setText(list.StartPrice.toMoney())

        }
        else if (list.ReserveState == 2){//NotMet The value of the highest bid has not reached the reserve price.
            holder.is_reserved!!.visibility = View.VISIBLE
            holder.reserved_price!!.visibility = View.VISIBLE

            holder.is_reserved!!.setText(R.string.main_listing_discover_no_reserve)
            holder.reserved_price!!.setText(list.StartPrice.toMoney())
        }
        else if (list.ReserveState == 3){//NotApplicable	 The listing cannot have a reserve because bidding is not allowed. This may be because it is a classified or because it is a Buy Now Only auction.
            holder.is_reserved!!.visibility = View.GONE
            holder.reserved_price!!.visibility = View.GONE
        }



    }





}
