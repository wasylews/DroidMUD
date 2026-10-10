/*
 * Copyright 2026 Vasyl Sabov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wasylews.droidmud

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.wasylews.droidmud.DroidMUDApp
import com.wasylews.droidmud.Server
import com.wasylews.droidmud.server.MudServer
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SmokeTest {
    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun testServerConnection() =
        runTest {
            MudServer().use {
                val server = Server("Test Server", "127.0.0.1", it.port)
                composeTestRule.setContent {
                    DroidMUDApp(listOf(server))
                }

                composeTestRule.onNodeWithText("Welcome to MUD server!").assertIsDisplayed()
            }
        }
}
