package com.mdy.sample.controller

import com.mdy.sample.service.CoroutineTestService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.util.StopWatch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/coroutine")
@RestController
class CoroutineTestController(
    private val coroutineTestService: CoroutineTestService,
    private val coroutineScope: CoroutineScope,
) {

    @GetMapping("/test")
    suspend fun getTest() {
        coroutineScope.launch {
            println("### [/api/coroutine/test] start ###")
            val stopWatch = StopWatch()
            stopWatch.start()
            val list = (1..33).toList()
            val res = coroutineTestService.doWork(list)
            println("### 최종 결과: $res ###")
            stopWatch.stop()
            println("### 걸린시간: ${stopWatch.totalTimeSeconds} 초")
            println("### [/api/coroutine/test] end ###")
        }
    }
}