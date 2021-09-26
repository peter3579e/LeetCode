package com.example.leetcode

import java.text.SimpleDateFormat
import kotlin.math.ln

class Answer2(capacity: Int) {

    private val cacheCapacity = capacity
    private val map = mutableMapOf<Int, Node>()
    private val head = Node(0, 0, 0.0, 0)
    private val tail = Node(0, 0, 0.0, 0)

    init {
        head.next = tail
        tail.prev = head
    }


    fun get(key: Int): Int {
        return if (map.containsKey(key)) {
            val node = map.get(key)
            node!!.value
        } else {
            -1
        }
    }

    fun put(key: Int, value: Int, weight: Double) {

        if (!map.containsKey(key)) {
            val time = System.currentTimeMillis()
            val score = (weight / -100)
            val node = Node(key, value, score, time)
            map.put(key, node)
            addToHead(node)
            moveNextNode(node)

            if (map.size > cacheCapacity) {
                val lastNode = tail.prev
                remove(lastNode!!)
                map.remove(lastNode.key)
            }
        } else {
            val node = map.get(key)
            val lastAccessTime = node!!.time
            node.value = value
            node.score = weight / ln((System.currentTimeMillis() - lastAccessTime).toDouble())
            node.time = System.currentTimeMillis()
            remove(node)
            addToHead(node)
            moveNextNode(node)
        }
    }

    fun addToHead(node: Node) {
        val next = head.next
        node.next = next
        next!!.prev = node
        head.next = node
        node.prev = head
    }


    private fun moveNextNode(node: Node) {
        val originalNextNode = node.next
        val original2NextNode = originalNextNode?.next

        if (node.score < originalNextNode!!.score && originalNextNode.key != 0) {
            remove(node)
            originalNextNode.next = node
            node.prev = originalNextNode
            node.next = original2NextNode
            original2NextNode?.prev = node
            return moveNextNode(node)
        }

    }

    private fun remove(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    data class Node(var key: Int = 0, var value: Int = 0, var score: Double, var time: Long = 0) {
        var next: Node? = null
        var prev: Node? = null
    }

}

/**
 * the answer does not use for loop, so the Big-O notation is O(1)
 */