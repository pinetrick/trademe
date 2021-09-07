package com.pine.mytrademetest.main_listings.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pine.mytrademetest.App
import com.pine.mytrademetest.R
import com.pine.mytrademetest.a
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

    /*
    * API Return Error: This list is free shipping list, however, no free shipping returned
    * and not all api return ReserveState field,
    * {
            "ListingId": 2149307328,
            "Title": "test",
            "Category": "4798-6111-7678-",
            "StartPrice": 3,
            "BuyNowPrice": 50,
            "StartDate": "/Date(1630978538133)/",
            "EndDate": "/Date(1631583338133)/",
            "ListingLength": null,
            "HasGallery": true,
            "AsAt": "/Date(1630978752792)/",
            "CategoryPath": "/Health-beauty/Weight-loss/Shapewear",
            "PictureHref": "https://images.tmsandbox.co.nz/photoserver/thumb/8759778.jpg",
            "IsNew": true,
            "Region": "Auckland",
            "Suburb": "Auckland City",
            "HasBuyNow": true,
            "NoteDate": "/Date(0)/",
            "PriceDisplay": "$3.00",
            "PromotionId": 2,
            "AdditionalData": {
                "BulletPoints": [],
                "Tags": []
            },
            "MemberId": 4007168
        },
    * */
    override fun onBindViewHolder(holder: ViewHolderDiscover, position: Int) {
        //get each list data
        var list = discoverFragment.lists[position]

        holder.listing_line!!.setOnClickListener { Toast.makeText(a(), "Placeholder: Intent to another activity (${list.ListingId})", Toast.LENGTH_LONG).show() }

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


        if (list.HasFreeShipping){
            holder.free_shipping!!.visibility = View.VISIBLE;
        }
        else{
            holder.free_shipping!!.visibility = View.GONE;
        }

        if (list.ReserveState == -1) {
            if (list.IsBuyNowOnly){
                list.ReserveState = 3;
            }
            else if ((list.StartPrice > 0)){
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
