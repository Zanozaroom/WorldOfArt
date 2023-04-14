package com.example.museumart_inner.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.museumart_inner.di.DaggerChicagoArtComponent
import com.example.museumart_api.chicagoMuseumDeps
import com.example.museumart_inner.di.ChicagoArtComponent

class ChicagoViewModelComponent(application: Application): AndroidViewModel(application) {
val chicagoArtComponent: ChicagoArtComponent by lazy {
    DaggerChicagoArtComponent.factory().create(application.chicagoMuseumDeps)
}
}
