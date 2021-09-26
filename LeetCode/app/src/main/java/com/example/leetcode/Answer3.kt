package com.example.leetcode

import java.io.IOException

class Answer3 {

    fun recur(n: Double, cur: Double?): Double {

        var tempN = n
        var tempCur = cur

        for (i in 0..n.toInt() - 2) {

            if (tempCur == null) {
                tempCur = 0.0
            }

            if (n < 2) {
                val e = IOException("Invalid Input")
                throw e
            }

            tempCur += (1 / (tempN * (tempN - 1)))
            tempN -= 1

            if (tempN == 2.0) {
                return 1 / tempN + tempCur
            }
        }

        return 0.0
    }
}
