package com.furkan.mortymap.di

import kotlinx.coroutines.delay
import kotlin.random.Random
import com.furkan.mortymap.BuildConfig

object NetworkLatency {
    @Volatile
    var enabled: Boolean = BuildConfig.DEBUG

    suspend fun delayForPage(
        page: Int,
        firstMs: Long = 600L,
        nextMs: Long = 1400L,
        jitterMs: Long = 700L
    ) {
        if (!enabled) return
        val base = if (page <= 1) firstMs else nextMs
        val jitter = if (jitterMs > 0) Random.nextLong(0, jitterMs) else 0L
        delay(base + jitter)
    }
}