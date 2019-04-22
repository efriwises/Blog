package com.febri.blogs

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.febri.blogs.config.Config
import com.febri.blogs.service.RequestHandler
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class Login : AppCompatActivity() {
    private var pd: ProgressDialog? = null
    private var params: HashMap<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        params = hashMapOf()
        btn_server_login.setOnClickListener {
            get_data_login().execute();
        }
        tv_registrasi.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
    inner class get_data_login : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd = ProgressDialog.show(this@Login,"","Wait", true, true)
        }

        override fun doInBackground(vararg param: String?): String {    //method Async

            val handler = RequestHandler()
            params?.put("email", et_email.text.toString())
            params?.put("pass", et_password.text.toString())

            val result = params?.let { handler.sendPostRequest(Config.url_login, it) }
            return result ?: ""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)


            try {
                pd?.dismiss()
                Log.d("result", result)
                val objek = JSONObject(result)
                if (objek.getInt("status") == 1) {
                    Toast.makeText(this@Login, "Login Gagal!", Toast.LENGTH_SHORT).show()
                } else {
                    Prefs.putString("email", et_email.text.toString())
                    Toast.makeText(this@Login, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

}
