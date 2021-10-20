package com.example.simplegetrequest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvName=findViewById<TextView>(R.id.tvName)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        var text=""
        if (apiInterface != null) {

            apiInterface.getDetails().enqueue(object : Callback<ArrayList<Names.Name>> {
                override fun onResponse(
                    call: Call<ArrayList<Names.Name>>,
                    response: Response<ArrayList<Names.Name>>
                ) {
                   progressDialog.dismiss()
                    Log.d("TAG", response.code().toString() + "")
                    for(data in response.body()!!) {
                        text+= data.name+"\n"
                    }
                    tvName.text=text


                }

                override fun onFailure(call: Call<ArrayList<Names.Name>>, t: Throwable) {
                   progressDialog.dismiss()
                    call.cancel()
                }
            })
        }
    }
}