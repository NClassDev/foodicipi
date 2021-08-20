package com.nclassdev.foodicipi.utils

fun <T> List<T>?.valueOrEmpty() : List<T> {
    return this ?: listOf()
}