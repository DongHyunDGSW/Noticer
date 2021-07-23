package kr.hs.dgsw.donghyeon.iframe_example.viewmodel.activites

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.donghyeon.data.DataModel
import kr.hs.dgsw.donghyeon.data.NumberItemModel
import kr.hs.dgsw.donghyeon.data.RequestModel
import kr.hs.dgsw.donghyeon.iframe_example.base.BaseViewModel
import kr.hs.dgsw.donghyeon.iframe_example.repo.ItemInfoRepository
import retrofit2.Response

class MainViewModel : BaseViewModel() {

    val isCompleted : MutableLiveData<Boolean> = MutableLiveData()
    val responseURL : MutableLiveData<String> = MutableLiveData()
    val responseExampleURL : String = "file:///android_asset/example.html"
    var responseData : ArrayList<NumberItemModel> = arrayListOf()
    private val repository = ItemInfoRepository()

    val getURL : LiveData<String>
        get() = responseURL

    init {
        isCompleted.value = false
        responseURL.value = ""
        getItemInfo(RequestModel("https://www.11st.co.kr/products/3058460519"))
    }


    fun getItemInfo(body : RequestModel) {
        addDisposable(repository.getItemInfo(body),
            object : DisposableSingleObserver<retrofit2.Response<DataModel>>() {
                override fun onSuccess(response: Response<DataModel>) {
                    if(response.isSuccessful) {
                        if(response.code() == 200) {
                            responseData.run {
                                add(response.body()?.item!!)
                                add(response.body()?.item1!!)
                                add(response.body()?.item2!!)
                                add(response.body()?.item3!!)
                                add(response.body()?.item4!!)
                                add(response.body()?.item5!!)
                                add(response.body()?.item6!!)
                                add(response.body()?.item7!!)
                                add(response.body()?.item8!!)
                                add(response.body()?.item9!!)
                            }
                            responseURL.value = response.body()?.webPage!!
                            isCompleted.value = true
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("MainViewModel", "${e.message}")
                }

            }
        )
    }
}