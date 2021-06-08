package com.simple.noticer.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.simple.noticer.R
import com.simple.data.model.ResSchoolParseData
import com.simple.data.model.Row
import com.simple.noticer.data.module.UIModule
import com.simple.data.net.RetrofitApi
import com.simple.data.net.RetrofitModule
import com.simple.noticer.databinding.ActivitySelectSchoolBinding
import com.simple.noticer.ui.adapter.SchoolAdapter
import com.simple.noticer.ui.adapter.listener.onSchoolItemListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectSchoolActivity : AppCompatActivity() {

    private val KEY = "503bcc6acfd4461f8fdf117c90319b51"
    private lateinit var schoolBinding: ActivitySelectSchoolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UIModule.setStatusBarTransparent(window, this, UIModule.CODE_MAIN)
        schoolBinding = ActivitySelectSchoolBinding.inflate(layoutInflater)
        setContentView(schoolBinding.root)

        updateWidget()
    }
    private fun updateWidget() {
        val service = RetrofitModule.getInstance()
        val api = service.create(RetrofitApi::class.java)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                api.getAllSchools(KEY).enqueue(object : Callback<ResSchoolParseData> {
                    override fun onResponse(
                        call: Call<ResSchoolParseData>,
                        response: Response<ResSchoolParseData>
                    ) {
                        if (response.code() == 200) {
                            schoolBinding.recyclerSearchSchool.layoutManager = LinearLayoutManager(this@SelectSchoolActivity)
                            schoolBinding.recyclerSearchSchool.adapter = SchoolAdapter(response.body()!!.schoolInfo!![1].row, object : onSchoolItemListener {
                                override fun onClickItemListener(data: Row) {
                                    createDialog(data)
                                }
                            })
                        } else {
                            Log.d("TAG", "${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ResSchoolParseData>, t: Throwable) {
                        Log.d("TAG", "${t.message}")
                    }
                })
            }
        }
        schoolBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                api.getAllSchools(KEY, "json", query!!).enqueue(object : Callback<ResSchoolParseData> {
                    override fun onResponse(
                            call: Call<ResSchoolParseData>,
                            response: Response<ResSchoolParseData>
                    ) {
                        if (response.code() == 200) {
                            if(response.body()!!.RESULT?.MESSAGE != "해당하는 데이터가 없습니다.") {
                                schoolBinding.recyclerSearchSchool.adapter = SchoolAdapter(response.body()!!.schoolInfo!![1].row, object : onSchoolItemListener {
                                    override fun onClickItemListener(data: Row) {
                                        createDialog(data)
                                    }
                                })
                            }else {
                                Snackbar.make(window.decorView.rootView, "검색 결과가 없습니다 .. ", Snackbar.LENGTH_LONG).show()
                            }
                        } else {
                            Log.d("TAG", "${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ResSchoolParseData>, t: Throwable) {
                        Log.d("TAG", "${t.message}")
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun createDialog(data : Row) {
        val alertBuilder = AlertDialog.Builder(this)
                .setTitle("정말 이 학교가 맞나요?")
                .setMessage("선택하신 학교는 ${data.SCHUL_NM}입니다.")
                .setCancelable(false)
                .setPositiveButton("맞아요.") { c ,v ->

                    finish()

                    val intent = Intent(applicationContext, InputActivity::class.java)
                    intent.putExtra("schoolName", data.SCHUL_NM)

                    startActivity(intent)
                    overridePendingTransition(R.anim.slowly_visible, 0)
                }
                .setNegativeButton("아닌 것 같아요.")  {c, v ->
                }
                .create()

        alertBuilder.show()
    }

    override fun onBackPressed() {
        return
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.slowly_gone)
    }
}