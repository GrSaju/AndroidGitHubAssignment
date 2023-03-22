package com.example.githubassignment.adapter
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubassignment.Interface.ShareLinkInterface
import com.example.githubassignment.R
import com.example.githubassignment.roomDatabase.TblRepositoryList
import com.google.android.material.card.MaterialCardView
import java.util.*

class GetRepositoryAdapter(
    private val contexts: Context,
    private var tblRepositoryLists: ArrayList<TblRepositoryList>,
    private var shareLinkInterface: ShareLinkInterface,
    ) :

    RecyclerView.Adapter<GetRepositoryAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, i: Int) {

        holder.tvRepositoryName!!.text = tblRepositoryLists[i].repositoryName

        if(tblRepositoryLists[i].description == "null"){
            holder.tvDescription!!.text = "-"
        } else {
            holder.tvDescription!!.text = tblRepositoryLists[i].description
        }

        holder.shareBtn!!.setOnClickListener {
            shareLinkInterface.shareLink(tblRepositoryLists[i].htmlUrl)
        }

        holder.cvLayout!!.setOnClickListener {
            try {
                if (tblRepositoryLists[i].htmlUrl == "") {
                    Log.e("", "")
                } else {
                    var url = tblRepositoryLists[i].htmlUrl
                    if (!url.contains("http://") && !url.contains("https://")) {
                        url = "https://$url"
                    } else if (url.startsWith("http://")) {
                        url = url.replace("http://", "https://")
                    }
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    contexts.startActivity(i)
                }

            } catch (e: ActivityNotFoundException) {

            }
        }

    }

    override fun getItemCount(): Int = tblRepositoryLists.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRepositoryName: TextView ?=null
        var tvDescription: TextView ?=null

        var shareBtn: RelativeLayout ?=null

        var cvLayout: MaterialCardView ?=null

        init {
            tvRepositoryName = itemView.findViewById(R.id.tvRepositoryName)
            tvDescription = itemView.findViewById(R.id.tvDescription)

            shareBtn = itemView.findViewById(R.id.shareBtn)

            cvLayout = itemView.findViewById(R.id.cvLayout)
        }

    }

}