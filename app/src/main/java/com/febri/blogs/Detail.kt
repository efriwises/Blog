package com.febri.blogs

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.febri.blogs.config.Config
import com.febri.blogs.model.ContentModel
import com.febri.blogs.service.RequestHandler
import kotlinx.android.synthetic.main.detail_activity.*
import org.json.JSONObject

class Detail : AppCompatActivity() {

    private var id: String? = null
    private var pd: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        id = intent.getStringExtra(Config.id)

        showData().execute()

    }

    inner class showData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            val request = RequestHandler()
            return request.sendGetRequest(Config.url_detail_content + id)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Detail, "", "Wait...", false, true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(this@Detail, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    txt_judul.text = data.getString("judul")
                    txt_content.text = data.getString("description")
                    Glide.with(this@Detail)
                        .load(Config.url_gambar + data.getString("gambar")).into(img_content)
                }

            }
        }

    }

}
