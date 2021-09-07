package com.pine.mytrademetest.funs

import java.text.DecimalFormat
import java.text.NumberFormat

fun Double.toMoney(): String{
    val nf: NumberFormat = DecimalFormat("$#,##0.00")
    return nf.format(this)
}

fun Double.toMoneyAutoDecimal(): String{
    val nf: NumberFormat = DecimalFormat("$#,##0.##")
    return nf.format(this)
}