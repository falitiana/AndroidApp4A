package com.example.android4a.Presentation.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.Data.local.models.MainController
import com.example.android4a.Presentation.main.Character
import com.example.android4a.R
import com.example.android4a.injection.Singletons
import com.example.android4a.injection.Singletons.getSharedPreferences
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity<loginSuccess> : AppCompatActivity() {

    //Koin crée les classes à la demande mais il faut les appeler
    val mainViewModel: MainViewModel by inject()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Koin crée la classe que quand on l'appelle. D'où la méthode onStartpour tester
        //mainViewModel.onStart()
        mainViewModel.loginLiveData.observe(this, Observer {
            when (it) {
                is LoginSucces -> {
                    //TODO Navigate

                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu")
                        .setPositiveButton("ok") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(
                login_edit.text.toString().trim(),
                password_edit.text.toString()
            )
        }
    }


    private var recyclerView: RecyclerView? = null
    private var mAdapter: ListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val sharedPreferences: SharedPreferences? = null
    private val gson: Gson? = null

    private var controller: MainController? = null


    @JvmName("onCreate1")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        controller = Singletons.gson?.let {
            MainController(
                this,
                it,
                getSharedPreferences(applicationContext)!!
            )
        }
        controller!!.onStart()
    }

    fun ShowList(characterList: kotlin.collections.List<Character?>?) {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager

        // define an adapter
        mAdapter = ListAdapter(characterList, object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            fun onItemClick(item: Character?) {
                controller!!.onItemClick(item)
            }
        })
        recyclerView!!.adapter = mAdapter
    }

    fun ShowError() {
        Toast.makeText(applicationContext, "API Error", Toast.LENGTH_SHORT).show()
    }

    fun navigateToDetails(character: Character?) {
        val myIntent = Intent(this@MainActivity, DetailActivity::class.java)
        myIntent.putExtra("CharacterKeyName", Character.getName())
        this@MainActivity.startActivity(myIntent)
    }

}

private fun <T> MutableLiveData<T>.observer(
    mainActivity: MainActivity<Any?>,
    observer: Observer<Any>
) {
    TODO("Not yet implemented")
}
