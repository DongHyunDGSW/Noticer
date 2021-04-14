package com.simple.noticer.data.module

import android.util.Log
import com.simple.noticer.data.model.ResSchoolParseData
import com.simple.noticer.data.model.RoomData
import com.simple.noticer.data.net.Network
import com.simple.noticer.data.net.RetrofitModule
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class ParseSchoolModule {
    companion object {
        private const val KEY = "503bcc6acfd4461f8fdf117c90319b51"
        val jobGetSchoolParseData : Deferred<ResSchoolParseData> = CoroutineScope(Dispatchers.Default).async {
            var arrSchoolList : ResSchoolParseData ?= null

            val getSchool : Job = GlobalScope.async {
                val service = RetrofitModule.getInstance()
                val api = service.create(Network::class.java)

                api.getAllSchools(KEY).enqueue(object : Callback<ResSchoolParseData> {
                    override fun onResponse(
                        call: Call<ResSchoolParseData>,
                        response: Response<ResSchoolParseData>
                    ) {
                        if(response.code() == 200) {
                            arrSchoolList = response.body()
                        }else{
                            Log.d("TAG", "${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ResSchoolParseData>, t: Throwable) {
                        Log.d("TAG", "${t.message}")
                    }
                })
            }

            getSchool.join()

            arrSchoolList!!
        }
    }
}