package com.febri.blogs

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.febri.blogs.config.Config
import com.febri.blogs.model.SubKategoriModel
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.fragment_home_adapter.view.*


class SubKategoriAdapter(
    private val mValues: List<SubKategoriModel>
) : RecyclerView.Adapter<SubKategoriAdapter.ViewHolder>() {
    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_home_adapter, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_subkategori
        holder.mContentView.text = item.subkategori
        context?.let { Glide.with(it).load(Config.url_gambar + item.icon ).into(holder.mImg!!) }

        holder.mLayout?.setOnClickListener{
            val intent= Intent(context, ContentList::class.java)
            intent.putExtra(Config.id,item.id_subkategori)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mLayout: LinearLayout? = mView.layoutAdapter
        val mImg : ImageView? = mView.img_icon

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
