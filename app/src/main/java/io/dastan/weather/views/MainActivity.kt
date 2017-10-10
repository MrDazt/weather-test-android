package io.dastan.weather.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import io.dastan.weather.R
import io.dastan.weather.adapter.MainCityAdapter
import io.dastan.weather.network.ApiClient
import io.dastan.weather.viewmodel.MainInterface
import io.dastan.weather.viewmodel.MainVM

class MainActivity : AppCompatActivity(), MainInterface {
    val viewModel = MainVM()

    lateinit var recyclerView: RecyclerView
    lateinit var pd: ProgressDialog

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.bind(this, this)

        setupUI()

        viewModel.loadData()
    }

    fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        viewModel.bindRecyclerView(recyclerView)

        pd = ProgressDialog(this)
        pd.setMessage(getString(R.string.loading))
    }

    override fun showAlert(title: String, message: String) {
        if (!isFinishing) {
            AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, null)
                    .show()
        }
    }

    override fun showNoInternet() {

    }

    override fun showIndicator() {
        pd.show()
    }

    override fun hideIndicator() {
        pd.dismiss()
    }
}
