package com.febri.blogs

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.febri.blogs.config.Config
import com.febri.blogs.model.ContentModel
import com.febri.blogs.service.RequestHandler
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject

class ContentList : AppCompatActivity() {

    private var id: String? = null
    private var pd: ProgressDialog? = null
    private var list: MutableList<ContentModel>? = null
    private var data: HashMap<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contentlist_activity)

        id = intent.getStringExtra(Config.id)

        data = hashMapOf()

        list = mutableListOf()
        showData().execute()
    }

    inner class showData : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@ContentList, "", "Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {
            val handler = RequestHandler()
            val result = handler.sendGetRequest(Config.url_content + id)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(this@ContentList, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ContentList, "ada data!", Toast.LENGTH_SHORT).show()
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = ContentModel()
                    model.id_content = data.getString("id_content")
                    model.judul = data.getString("judul")
                    list?.add(model)
                    val adapter = list?.let { ContentListAdapter(it) }
                    rc.layoutManager = LinearLayoutManager(this@ContentList)
                    rc.adapter = adapter
                }
            }
        }
    }

}
