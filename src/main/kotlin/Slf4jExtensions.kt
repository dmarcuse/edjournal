package me.apemanzilla.edjournal

import org.slf4j.LoggerFactory

/** Construct a logger for [T] using [LoggerFactory.getLogger] */
inline fun <reified T> logger() = LoggerFactory.getLogger(T::class.java)
