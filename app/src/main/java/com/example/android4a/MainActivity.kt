package com.example.android4a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    //Koin crée les classes à la demande mais il faut les appeler
    val mainViewModel:MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Koin crée la classe que quand on l'appelle. D'où la méthode onStartpour tester
        //mainViewModel.onStart()

        main_button.setOnClickListener {
            mainViewModel.onClickedIncrement()
        }
        mainViewModel.counter.observe(this, Observer {
           value -> main_text.text= value.toString()
        } )
    }
}