package com.febri.blogs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.febri.blogs.model.SubKategoriModel
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
