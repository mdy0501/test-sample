package com.mdy.sample.configuration

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoroutineScopeConfig {
    @Bean
    fun coroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.Default)
}