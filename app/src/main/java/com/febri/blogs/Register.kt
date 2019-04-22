package com.febri.blogs

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.febri.blogs.config.Config
import com.febri.blogs.service.RequestHandler
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class Register : AppCompatActivity() {

    private var pd: ProgressDialog? = null
    private var param_data: HashMap<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        param_data = hashMapOf()

        btn_registrasi.setOnClickListener {
            goRegister().execute()
        }
    }
    inner class goRegister : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd = ProgressDialog.show(this@Register,"","Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {
            val handler = RequestHandler()
            param_data?.put("email", et_email.text.toString())
            param_data?.put("pass", et_password.text.toString())
            param_data?.put("nama", et_nama.text.toString())

            val result = param_data?.let { handler.sendPostRequest(Config.url_registrasi, it) }
            return result ?: ""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)


            try {
                pd?.dismiss()
                Log.d("result", result)
                val objek = JSONObject(result)
                if (objek.getInt("status") == 1) {
                    Toast.makeText(this@Register, objek.getString("response"), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@Register, objek.getString("response"), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Register, Login::class.java))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}
