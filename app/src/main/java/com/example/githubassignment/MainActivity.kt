package com.example.githubassignment

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubassignment.Interface.ShareLinkInterface
import com.example.githubassignment.adapter.GetRepositoryAdapter
import com.example.githubassignment.roomDatabase.OfflineRoomDatabase
import com.example.githubassignment.roomDatabase.TblRepositoryList
import com.example.githubassignment.viewModel.MainViewModel


class MainActivity : AppCompatActivity(), ShareLinkInterface {
//    private lateinit var binding: ActivityMainBinding  //defining the binding class

    private var offlineRoomDatabase: OfflineRoomDatabase? = null
     private lateinit var getRepositoryAdapter: GetRepositoryAdapter

     private var rrRepositoryList: RecyclerView ?= null

    private var repositoryList: List<TblRepositoryList> = ArrayList()

    private val user = "GrSaju"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        rrRepositoryList = findViewById(R.id.rrRepositoryList)

        initialWorks()

        clickListeners()
    }



    /**
     * The method will run, when the page reloads
     * */
    private fun initialWorks() {
        offlineRoomDatabase = OfflineRoomDatabase.getDataBaseInstance(this)
        getRepositoryAPI()
    }



    /**
     * This method is used to control the
     * clickable widgets
     * */
    private fun clickListeners() {

    }



    /**
     * This function is used to bind the data that comes from the view model
     * and inserting into the database
     * */
    private fun getRepositoryAPI(){
        repositoryList = ArrayList()
        offlineRoomDatabase!!.iTblGetRepository ()!!
            .reset(offlineRoomDatabase!!.iTblGetRepository()!!.getOfflineRepositoryList())

        val getRepositoryData = ViewModelProvider(this)[MainViewModel::class.java]
        getRepositoryData.getUserRepositoryData(user).observe(this@MainActivity){
            for(i in it.indices) {
                val tblGetRepositoryList = TblRepositoryList()

                tblGetRepositoryList.repositoryName = it[i].name.toString()
                tblGetRepositoryList.description = it[i].description.toString()
                tblGetRepositoryList.fullName = it[i].fullName.toString()
                tblGetRepositoryList.ownerName = it[i].owner!!.login.toString()
                tblGetRepositoryList.htmlUrl = it[i].htmlUrl.toString()

                offlineRoomDatabase!!.iTblGetRepository()!!.insertRepositories(tblGetRepositoryList)

            }
            repositoryList = offlineRoomDatabase!!.iTblGetRepository()!!.getOfflineRepositoryList()
            getRepositoryAdapter = GetRepositoryAdapter(this@MainActivity, repositoryList as ArrayList, this)
            rrRepositoryList!!.layoutManager = LinearLayoutManager(this@MainActivity)
            rrRepositoryList!!.adapter = getRepositoryAdapter
        }
    }



    /**
     * This method is used for sharing the repository url
     * to the other applications
     * */
    @JavascriptInterface
    fun sendMessage(url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this GitHub repository link")
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            Html.fromHtml("Share the repository link to friends $url"))

        startActivity(Intent.createChooser(intent, "Share via"))
    }



    override fun shareLink(url: String) {
        sendMessage(url)
    }

}