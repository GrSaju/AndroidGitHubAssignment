package com.example.githubassignment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubassignment.R
import com.example.githubassignment.utlis.NetworkUtil
import com.example.githubassignment.viewModel.MainViewModel

class AddRepositoryActivity: AppCompatActivity() {

    private var etRepName: EditText ?=null
    private var etOwnerName: EditText ?=null

    private var tvAddBtn: TextView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_repository_activity)

        etRepName = findViewById(R.id.etRepName)
        etOwnerName = findViewById(R.id.etOwnerName)
        tvAddBtn = findViewById(R.id.tvAddBtn)

        clickListeners()

    }



    /**
     * This method is used to control the
     * clickable widgets
     * */
    private fun clickListeners() {
        tvAddBtn!!.setOnClickListener {
            if(etRepName!!.text.isEmpty()){
                Toast.makeText(this@AddRepositoryActivity, getString(R.string.enter_rep_warning),
                    Toast.LENGTH_SHORT).show()
            } else {
                createRepositoryAPI(etRepName!!.text.toString())
            }

        }
    }



    /**
     * This function is used to bind the data that comes from the view model
     * and creating the repository
     * */
    private fun createRepositoryAPI(name: String){
        if(NetworkUtil.checkActiveInternetConnection(this@AddRepositoryActivity)) {
            val createRepositoryData = ViewModelProvider(this)[MainViewModel::class.java]
            createRepositoryData.createRepositoryData(name).observe(this@AddRepositoryActivity){
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        } else {
            Toast.makeText(this@AddRepositoryActivity, getString(R.string.no_network),
                Toast.LENGTH_SHORT).show()
        }

    }
}