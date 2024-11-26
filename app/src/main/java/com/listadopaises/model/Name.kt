package com.listadopaises.model

data class Name(
    val common: String?,
    val official: String?,
    val nativeName: Map<String, NativeName>?
)

data class NativeName(
    val official: String?,
    val common: String?
)