package xyz.wingio.logra.utils.logcat

import xyz.wingio.logra.domain.logcat.LogcatEntry
import java.io.InputStream
import kotlin.concurrent.thread

object LogcatManager {

    private const val command = "logcat"

    private lateinit var currentStr: InputStream

    fun connect() {
        currentStr = Runtime.getRuntime().exec(command).inputStream
    }

    fun listen(callback: (LogcatEntry) -> Unit) {
        thread(start = true) {
            val reader = currentStr.bufferedReader()
            while (reader.readLine() != null && reader.readLine().isNotEmpty()) {
                val line = reader.readLine()
                line.split("\n").forEach {
                    try {
                        val ent = LogcatEntry.fromLine(it)
                        ent?.let(callback)
                    } catch (th: Throwable) {
                    }
                }
            }
        }
    }

}