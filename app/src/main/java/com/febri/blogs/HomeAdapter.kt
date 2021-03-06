package com.febri.blogs

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.febri.blogs.config.Config


import com.febri.blogs.model.KategoriModel

import kotlinx.android.synthetic.main.fragment_home_adapter.view.*

class HomeAdapter(
    private val mValues: List<KategoriModel>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_home_adapter, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_kategori
        holder.mContentView.text = item.kategori

        holder.mLayout?.setOnClickListener{
//            Toast.makeText(context, item.id_kategori, Toast.LENGTH_SHORT).show()
            val intent= Intent(context, SubKategori::class.java)
            intent.putExtra(Config.id,item.id_kategori)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mLayout: LinearLayout? = mView.layoutAdapter

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}

