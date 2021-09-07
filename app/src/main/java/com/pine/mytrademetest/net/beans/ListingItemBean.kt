package com.pine.mytrademetest.net.beans

class ListingItemBean {
    var ListingId: Long = 0;
    var Title: String = "";
    var PictureHref: String = "";
    var Region: String = "";
    var StartPrice: Double = -1.0;
    var BuyNowPrice: Double = -1.0;
    var PriceDisplay: String = "";
    var HasFreeShipping: Boolean = false;
    var ReserveState: Int = -1;
    var IsClassified: Boolean = false;
    var IsBuyNowOnly: Boolean = false;

}