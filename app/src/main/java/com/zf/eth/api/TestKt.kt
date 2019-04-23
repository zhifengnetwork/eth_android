package com.zf.eth.api


import java.math.BigDecimal
import java.text.DecimalFormat


fun main(args: Array<String>) {

//    println("")
//
//    var a = 2
//
//    var b = 10
//
//    b = a ?: b

//    print(">>>:$b")

//    var arr2: Array<Array<String>> = Array(1) { arrayOf("") }

//    val arr: Array<Array<String>> = arrayOf(arrayOf("a", "b"), arrayOf("c", "d"), arrayOf("e", "f"))
//    print(">>>>:" + arr[0][1])


    var arr2 = Array<Array<String>>(1) { arrayOf("") }


//    val jsonList = ArrayList<PourNumBean>()
//    jsonList.add(PourNumBean("123", "2"))
//    jsonList.add(PourNumBean("231", "1"))
//    val finalJson = Gson().toJson(jsonList)
//    print(">>>>>>:$finalJson")

    val a = "0.5"
    val b = "0.00003".toFloat()
    val b2 = DecimalFormat("#.00000").format(b)
    print("$b2   ")
    val c = (BigDecimal(a).multiply(BigDecimal(b2))).toString()
    print(c)


}
