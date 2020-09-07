package com.example.android4a.injection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Koin permet de créer nos classes plus facilement avec des Singletons etc...
// Pour que ça marche, il faut étendre l'Application Android à l'objet EsieaApplication
// Il faut ajouter dans AndroidManifest l'application que l'on pointe! ici EsieaApplication


class EsieaApplication:Application(){
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@EsieaApplication)
            // modules
            modules(presentationModule)
        }
    }
}