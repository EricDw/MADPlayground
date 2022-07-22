package com.example.madplayground.helpers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
fun runUnconfinedTest(
    testBody: suspend TestScope.() -> Unit
) = runTest(
    context = UnconfinedTestDispatcher(),
    testBody = testBody
)