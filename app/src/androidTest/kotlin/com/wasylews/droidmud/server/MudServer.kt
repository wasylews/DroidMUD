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
package com.wasylews.droidmud.server

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.Closeable
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException

/**
 * A fake MUD server used for local E2E tests.
 */
class MudServer : Closeable {
    private val scope = CoroutineScope(Dispatchers.IO)

    private val serverSocket = ServerSocket(0)
    private var clientSocket: Socket? = null

    /**
     * Which port we are listening on.
     */
    val port: Int get() = serverSocket.localPort

    init {
        scope.launch { listen() }
    }

    private fun listen() {
        clientSocket =
            try {
                serverSocket.accept()
            } catch (_: SocketException) {
                // Server is shutting down
                return
            }

        val out = clientSocket?.getOutputStream()
        out?.write("Welcome to MUD server!\r\n".toByteArray(Charsets.ISO_8859_1))
        out?.flush()
    }

    /**
     * Close the connection.
     */
    override fun close() {
        serverSocket.close()
        clientSocket?.close()
        scope.cancel()
    }
}
