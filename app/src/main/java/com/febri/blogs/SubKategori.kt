package com.febri.blogs

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.febri.blogs.config.Config
import com.febri.blogs.model.SubKategoriModel
import com.febri.blogs.service.RequestHandler

import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject


class SubKategori : AppCompatActivity() {
    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var list:MutableList<SubKategoriModel>?=null
    private var data: HashMap<String, String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)


        id=intent.getStringExtra(Config.id)


        data = hashMapOf()

        list= mutableListOf()
        showData().execute()
    }
    inner class showData : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@SubKategori,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {
            val handler = RequestHandler()
            val result=handler.sendGetRequest(Config.url_sub_kategori_get+id)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@SubKategori, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@SubKategori, "ada data!", Toast.LENGTH_SHORT).show()
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = SubKategoriModel()
                    model.id_subkategori = data.getString("id_subkategori")
                    model.subkategori = data.getString("subkategori")
                    list?.add(model)
                    val adapter = list?.let { SubKategoriAdapter(it) }
                    rc.layoutManager = LinearLayoutManager(this@SubKategori)
                    rc.adapter = adapter
                }
            }
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}
