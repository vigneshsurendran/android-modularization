package com.azabost.quest.logging

class TestRecordingLoggerFactory : Logger.Factory  {
    private val loggers = mutableMapOf<String, TestRecordingLogger>()

    val debugMessages = mutableListOf<LogRecord>()
    val infoMessages = mutableListOf<LogRecord>()
    val errorMessages = mutableListOf<LogRecord>()

    @Synchronized
    override fun create(name: String): Logger =
        loggers.getOrPut(name, { TestRecordingLogger(name) })

    inner class TestRecordingLogger(override val name: String): Logger {
        @Synchronized
        override fun debug(message: String) {
            debugMessages.add(LogRecord(name, message, null))
        }

        @Synchronized
        override fun info(message: String) {
            infoMessages.add(LogRecord(name, message, null))
        }

        @Synchronized
        override fun error(message: String, throwable: Throwable?) {
            errorMessages.add(LogRecord(name, message, throwable))
        }
    }
}

class LogRecord(val loggerName: String, val message: String, val throwable: Throwable?)