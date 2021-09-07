package com.example.leetcode

import java.util.*
import kotlin.collections.HashMap

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return null
    }
}

class SolutionForReverseInteger {
    var reversedNumber = 0
    fun reverse(x: Int): Int {
        var y = x
        while (y != 0) {
            val pop = y % 10
            y /= 10
            if (reversedNumber > Int.MAX_VALUE / 10 || reversedNumber == Int.MAX_VALUE / 10 && pop > 7) return 0
            if (reversedNumber < Int.MIN_VALUE / 10 || reversedNumber == Int.MIN_VALUE / 10 && pop > -8) return 0
            reversedNumber = reversedNumber * 10 + pop
        }
        println("the value of reversedNumber =\n $reversedNumber ")
        return reversedNumber
    }
}

class SolutionPalindromeNumber() {
    fun isPalindrome(x: Int): Boolean {

        var reversedNumber = 0
        var y = x
        while (y != 0) {
            val pop = y % 10
            y /= 10
            reversedNumber = reversedNumber * 10 + pop
        }
        return if (x < 0 || x != reversedNumber) {
            false
        } else {
            reversedNumber == x
        }
    }
}

class SolutionRomanNumerals() {

    private val listRoman = mutableListOf<Int>()
    fun romanToInt(s: String): Int {

        val stringArray = s.split("")
        for (i in stringArray.indices) {
            if (stringArray[i] != "") {
                when (stringArray[i]) {
                    "I" -> listRoman.add(1)
                    "V" -> listRoman.add(5)
                    "X" -> listRoman.add(10)
                    "L" -> listRoman.add(50)
                    "C" -> listRoman.add(100)
                    "D" -> listRoman.add(500)
                    "M" -> listRoman.add(1000)
                }
            }
        }
//        println("the value of input String array = $listRoman")

        val finalResultList = mutableListOf<Int>()

        for ((count, i) in listRoman.indices.withIndex()) {

            if (count == 0){
                finalResultList.add(listRoman[i])
            }else{
                if (finalResultList[i - 1] < listRoman[i]){
                    finalResultList.add(listRoman[i] - (2 * listRoman[i - 1]))
                }else{
                    finalResultList.add(listRoman[i])
                }
            }
        }

        print(" the final result list = ${finalResultList.sum()} \n")

        return finalResultList.sum()
    }
}

class BetterSolution() {

    fun romanToInt(s: String): Int {
        val map: HashMap<Char, Int> = HashMap()
        map['I'] = 1
        map['V'] = 5
        map['X'] = 10
        map['L'] = 50
        map['C'] = 100
        map['D'] = 500
        map['M'] = 1000
        val n = s.length
        var res = map[s[n - 1]]!!
        for (i in s.length - 2 downTo 0) {
            print("the value of i $i \n")
            if (map[s[i]]!! < map[s[i + 1]]!!) res = res - map[s[i]]!! else res += map[s[i]]!!
        }
        return res
    }
}

class CommonPrefixSolution(){
    var list = mutableListOf<String>()
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isNotEmpty() && strs.size != 1){
            for (i in 1 until strs.size){
                list.add(strs[0].commonPrefixWith(strs[i]))
//                print("the result of prefix = ${list} \n")
            }
            return if (list.min().isNullOrEmpty()){
                ""
            }else{
                list.min()!!
            }
        }else if (strs.size ==1){
            return strs[0]
        }

        return ""
    }
}

class ParentheseSolution (){
    private var mappings = HashMap<Char, Char>()


    fun isValid(s: String): Boolean {

        mappings = HashMap()
        mappings[')'] = '('
        mappings['}'] = '{'
        mappings[']'] = '['

        val stack: Stack<Char> = Stack<Char>()

        for (i in s.indices){
            val scanChar = s[i]


            print("if value of stack before if = ${stack} \n")

            print("the value of scanKey = ${mappings.containsKey(scanChar)} \n")

            if (mappings.containsKey(scanChar)){

                print("if value of stack after if  = ${stack} \n")

                val topElement = if (stack.isEmpty()) '#' else stack.pop()


                print("the value of topElement = $topElement \n")

                print("the value of mappingScanChar = ${mappings[scanChar]} \n")

                if (topElement != mappings[scanChar]) {
                    return false;
                }
            }else{
                stack.push(scanChar)
            }
        }
        return stack.isEmpty()

    }
}

data class Person(var name: String = "", var age: Int = 0, var city: String = "")
class Person2 ()

class ScopeFunction(){
    companion object {
        @JvmStatic
        fun runThis(){
            val adam = Person().apply {
                name = "Peter"
                age = 20                       // same as this.age = 20 or adam.age = 20
                city = "London"
            }


            val one = Person()
            val two = Person().apply {
                name = "Peter"
            }
            println(one.name === two.name)

            val str = "Hello"
            // this
            str.run {
                println("The receiver string length: $length")
                //println("The receiver string length: ${this.length}") // does the same
            }

            // it
            str.let {
                println("The receiver string's length is ${it.length}")
            }

            val numbers = mutableListOf("one", "two", "three")
            val countEndsWithE = numbers.run {
                add("four")
                add("five")
                count { it.endsWith("e") }
            }
            with(numbers){
                add("six")
                add("seven")
            }
            numbers.let {
                it.add("eight")
                it.add("night")
            }
            println("$numbers /n")
            println("There are $countEndsWithE elements that end with e.")

            val numbers1 = mutableListOf("one", "two", "three", "four", "five")
            val resultList = numbers1.map { it.length }.filter { it > 3 }
            println(resultList)
        }
    }
}

class RemoveDuplicateNumber(){
    var size = 1
    fun removeDuplicates(nums: IntArray): Int {

        if (nums.isEmpty()) return  0
        else{
            var compareNumber = nums[0]
            nums.forEachIndexed { index, i ->
                if (nums[index] > compareNumber){
                    compareNumber = nums[index]
                    nums[size] = nums[index]
                    size ++
                }

            }
        }
        return size
    }
}

class RemoveElement(){
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var size = 0
        if (nums.isEmpty()) return 0
        else {
            nums.forEachIndexed { index, i ->
                if (nums[index] != `val`){
                    nums[size] = nums[index]
                    size ++
                }
            }
        }

        return size
    }
}

class ImplementStr(){
    fun strStr(haystack: String, needle: String): Int {
        var i = 0
        while (true) {
            var j = 0
            while (true) {
                if (j == needle.length) return i
                if (i + j == haystack.length) return -1
                if (needle[j] != haystack[i + j]) break
                j++
            }
            i++
        }
    }
}

class SearchInsert(){
    fun searchInsert(nums: IntArray, target: Int): Int {
        for (i in nums.indices){
         if (nums[i] == target){
             return i
         }else{
             while (nums[i]>target){
                 return i
             }
         }
        }
        return nums.size
    }
}

class MaximumSubarray(){
    fun maxSubArray(nums: IntArray): Int {
        var cMaxInt = nums[0]
        var maxInt = nums[0]
        nums.mapIndexed{i,it->
            if (i>0){
                cMaxInt = maxOf(it, cMaxInt + it)
                maxInt = maxOf(cMaxInt, maxInt)
            }
        }
        return maxInt
    }
}

class LengthOfLastWorld(){
    fun lengthOfLastWord(s: String): Int {
        return if (s.replace(" ", "").isEmpty()) 0 else s.split(" ").filter{ it->it!=""}.reversed()[0].length
    }
}

class PlusOne {


    fun plusOne(digits: IntArray): IntArray {

        for (i in digits.size - 1 downTo 0) {
            digits[i] += 1
            if (digits[i] <= 9) return digits
            digits[i] = 0
        }
        val arr = IntArray(digits.size + 1)
        arr[0] = 1

        return arr

    }
}

class AddBinary {
    fun addBinary(a: String, b: String): String {
        if(a=="0" && b=="0"){
            return "0"
        }
        else if(a=="0"){
            return b
        }
        else if(b=="0"){
            return a
        }
        var z = ""
        val ra = a.reversed()
        val rb = b.reversed()
        val la = a.length
        val lb = b.length
        var ta = ""
        var tb = ""
        var k = "0"
        for (i in 0 until if(la>=lb) la else lb){
            ta = if(la>i) ra.get(i).toString() else "0"
            tb = if(lb>i) rb.get(i).toString() else "0"
            if(ta=="0" && tb=="0"){
                z = k+z
                k = "0"
            }
            else if(ta=="1" && tb=="1"){
                z = if (k=="1") "1"+z else "0"+z
                k = "1"
            }
            else if(ta=="1" || tb=="1"){
                z = if (k=="1") "0"+z else "1"+z
                k = if (k=="1") "1" else "0"
            }
        }
        z = if(k=="1") "1"+z else z
        for (i in 0 until z.length){
            if(z.get(0).toString()=="1"){
                break
            }
            z = z.substring(1)
        }
        return z
    }
}

class MySqrt {
    fun mySqrt(x: Int): Int {
        if (x==0 || x==1){
            return x
        }
        var l = 0
        var h = x
        var m = 0
        var s = 0
        while (l <= h){
            m = l + (h-l) / 2
            s = x / m
            if (s==m){
                return m
            }
            else if (s < m){
                h = m-1
            }
            else{
                l = m + 1
            }
        }
        return h
    }
}

class ClimbStairs {
    fun climbStairs(n: Int): Int {

        var one = 1
        var two = 1

        for (i in 1 until n){
            val temp = one
            one += two
            two = temp
            print("the value of one $one \n")
        }
      return one
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null


}

class MergeTwoLists() {
    companion object{
        fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
            print("the value of l1 = ${l1?.`val`} ")
            print("the value of l2 = ${l2?.`val`} \n")
            if (l1 == null && l2 == null) {
                print(" print 0")
                return null
            }
            if (l1 == null) {
                print(" print 1 ${l2!!.`val`} \n")
                return l2
            }
            if (l2 == null) {
                print(" print 2 ${l1.`val`} \n")
                return l1
            }
            if (l1.`val` < l2.`val`) {
                l1.next = mergeTwoLists(l1.next, l2)
                print(" print 3 ${l1.`val`} \n")
                return l1
            }
            l2.next = mergeTwoLists(l2.next, l1)
            print("  print 4  ${l2.`val`} \n")
            return l2
        }
    }
}

class DeleteDuplicates {
    fun deleteDuplicates(head: ListNode?): ListNode? {

        var currentNode = head

        while (currentNode?.next != null){
            if (currentNode.`val` == currentNode.next!!.`val`){
                currentNode.next = currentNode.next!!.next
            }else{
                currentNode = currentNode.next
            }
        }
        return head
    }
}

class DeleteDuplicatesMedium {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var currentNode = head
        var dummy = ListNode(0)
        dummy.next = head

        while (currentNode != null){
            if (currentNode.next !=null && currentNode.`val` == currentNode.next!!.`val`){

                while ( currentNode!!.next != null && currentNode.`val` == currentNode.next!!.`val`){
                    currentNode = currentNode.next
                }
                dummy = dummy.next!!
            }
            currentNode = currentNode.next
        }
        return dummy.next
    }
}

class Oursky {
    fun test(firstList:List<String>,secondList:List<String>):Boolean{
        if (firstList.isNotEmpty() && secondList.isNotEmpty()){
             for (i in secondList.indices){
                 if (!firstList.contains(secondList[i])){
                     return false
                 }
             }

        }
     return true
    }
}


fun main() {
//    val solution = Solution()
//    val check = solution.twoSum(intArrayOf(0,1,2,3,4,5,6),11)
//    for (i in check!!.indices){
//        println("the value of check = ${check[i]}")
//    }

//    val reverse = SolutionForReverseInteger()
//    println("result of function = ${reverse.reverse(1463847412)}")

//    val palindrome = SolutionPalindromeNumber()
//    println("result of function = ${palindrome.isPalindrome(-101)}")

//    val romanNumerals = BetterSolution()
//   print("the result = ${romanNumerals.romanToInt("LVIII")}")

//    val commonPrefix = CommonPrefixSolution()
//    print("the result of prefix = ${commonPrefix.longestCommonPrefix(arrayOf("a"))}")

//    val parentheses = ParentheseSolution()
//    print("the result of prefix = ${parentheses.isValid("(((((())))))")} \n")]

//    val l1 = ListNode(1)
//    val l2 = ListNode(2)
//    val l3 = ListNode(4)
//    l1.next = l2
//    l2.next = l3
//
//    val l4 = ListNode(1)
//    val l5 = ListNode(3)
//    val l6 = ListNode(4)
//
//    l4.next = l5
//    l5.next = l6

//    ScopeFunction.runThis()


    fun checkList(list:ListNode?){
        if (list != null){
            print(list.`val`)
            checkList(list.next)
        }
    }

//    val mySqrt = MySqrt()
//    print(mySqrt.mySqrt(8))

//    val climb = ClimbStairs()
//    print(" the value of climb stair =  ${climb.climbStairs(5)} \n")

    val  l1 = ListNode(1)
    val  l2 = ListNode(1)
    val  l3 = ListNode(2)
    val  l4 = ListNode(3)
    val  l5 = ListNode(3)

    l1.next = l2
    l2.next = l3
    l3.next = l4
    l4.next = l5

//    val delete = DeleteDuplicatesMedium()
//    checkList(delete.deleteDuplicates(l1))

    val oursky = Oursky()
    print(oursky.test(listOf("A","D","E"), listOf("A","A","D","E")))





}




