package com.febri.blogs

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.febri.blogs.config.Config

import com.febri.blogs.model.KategoriModel
import com.febri.blogs.service.RequestHandler
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class HomeFragment : Fragment() {

    private var pd: ProgressDialog?=null
    private var list:MutableList<KategoriModel>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list= mutableListOf()
        showData().execute()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    inner class showData : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(context,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {
            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_kategori)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(activity, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = KategoriModel()
                    model.id_kategori = data.getString("id_kategori")
                    model.kategori = data.getString("kategori")
                    list?.add(model)
                    val adapter = list?.let { HomeAdapter(it) }
                    rc.layoutManager = LinearLayoutManager(activity)
                    rc.adapter = adapter
                }
            }
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}
