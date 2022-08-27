package info.firozansari

import com.github.ajalt.mordant.terminal.Terminal
import info.firozansari.PrintFormat.*
import java.util.concurrent.TimeUnit

fun print(message: String, format: PrintFormat = INFO) {
    val terminal = Terminal()
    when(format) {
        SUCCESS -> terminal.success(message = message)
        DANGER -> terminal.danger(message = message)
        WARNING -> terminal.warning(message = message)
        else -> terminal.info(message = message)
    }
}

fun addDivider() {
    println("_".repeat(30))
}

fun addDelay() {
    TimeUnit.SECONDS.sleep(1L)
}