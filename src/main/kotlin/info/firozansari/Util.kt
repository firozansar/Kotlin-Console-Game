package info.firozansari

import com.github.ajalt.mordant.terminal.Terminal
import info.firozansari.PrintFormat.*

enum class PrintFormat { SUCCESS, DANGER, WARNING, INFO }

fun customPrint(message: String, format: PrintFormat = INFO) {
    val terminal = Terminal()
    when(format) {
        SUCCESS -> terminal.success(message = message)
        DANGER -> terminal.danger(message = message)
        WARNING -> terminal.warning(message = message)
        else -> terminal.info(message = message)
    }
}