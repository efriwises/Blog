package com.febri.blogs

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.febri.blogs.config.Config

import com.febri.blogs.model.ContentModel

import kotlinx.android.synthetic.main.contentlist_adapter.view.*

class ContentListAdapter(
    private val mValues: List<ContentModel>
) : RecyclerView.Adapter<ContentListAdapter.ViewHolder>() {
    private var context: Context? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contentlist_adapter, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_content
        holder.mContentView.text = item.judul
        holder.mLayout?.setOnClickListener{
            val intent= Intent(context, Detail::class.java)
            intent.putExtra(Config.id,item.id_content)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mLayout: LinearLayout = mView.layout

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
