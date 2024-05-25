package com.mdy.sample.service

import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CoroutineTestService {
    suspend fun doWork(list: List<Int>): List<String> {
        val mutableList = mutableListOf<String>()
        val chunkedLists = list.chunked(5)
        chunkedLists.forEach { sublist ->
            val resList = processSublist(sublist)
            resList.forEach { res ->
                mutableList.add(res)
            }
        }

        return mutableList.toList()
    }

    private suspend fun processSublist(sublist: List<Int>) = coroutineScope {
        val mutableList = mutableListOf<String>()
        sublist.map { id ->
            async {
                println("### [id: $id] | name: ${Thread.currentThread().name} | time: ${LocalDateTime.now()} ###")
                val res = doHardWork(id = id)
                mutableList.add("_${res}_")
            }
        }.awaitAll()
        return@coroutineScope mutableList.toList()
    }

    suspend fun doHardWork(id: Int, millisecond: Long = 3000): Int {
        delay(millisecond)
        return id
    }
}