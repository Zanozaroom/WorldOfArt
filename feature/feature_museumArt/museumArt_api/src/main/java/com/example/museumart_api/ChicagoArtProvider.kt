package com.example.museumart_api

import android.app.Application
import android.content.Context

interface ChicagoArtProvider {
    val chicagoMuseumDeps: ChicagoMuseumDeps
}

val Context.chicagoMuseumDeps: ChicagoMuseumDeps
get() = when(this){
    is ChicagoMuseumDeps -> this
    is ChicagoArtProvider -> this.chicagoMuseumDeps
    is Application -> error("Application must implements ChicagoArtProvider")
    else -> applicationContext.chicagoMuseumDeps
}
