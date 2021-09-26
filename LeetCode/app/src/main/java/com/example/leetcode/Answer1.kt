package com.example.leetcode

class Answer1 {
    fun question(firstList: ArrayList<String>, secondList: ArrayList<String>): Boolean {
        if (firstList.isNotEmpty() && secondList.isNotEmpty()) {
            for (i in secondList.indices) {
                if (firstList.binarySearch(secondList[i]) < 0) {
                    return false
                }
            }
        }
        return true
    }
}


// array = [1,2,3,3]  array = [1,2,3]

fun num ( n:Int ): Int{

    if ( n <= 0){
        return 1
    }else{
        return num(n-1)+ num(n-1)
    }

}


//array = [1,2,5,3,3]   array = [1,2,3]








class Solution1(){

    fun findQuestionMark(s: String): String{

        val map = mutableMapOf<Int,String>()

        s.forEachIndexed { index, c ->
            map.put(index, s[index].toString())
            if ( map.containsValue("?")){
                return "Yes"
            }
        }

        return "No"


    }

}


fun main(){
    val answer = Solution1()
    print(answer.findQuestionMark("123"))
}


/**
 * the answer use for loop, so the Big-O notation is O(n^2)
 */