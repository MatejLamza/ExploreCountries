package com.example.exploretheworld.di.modules

import org.koin.core.qualifier.named

object Qualifiers {
    val apiFullURL by lazy { named("apiFullURL") }

}