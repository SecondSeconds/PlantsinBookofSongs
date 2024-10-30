package com.example.plantsinbookofsongs.vm

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.screen.aiscreen.log
import com.example.plantsinbookofsongs.service.WebService
import com.example.plantsinbookofsongs.utils.ConvertImage.Companion.BitmapToString
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


var ACCESS_TOKEN="24.cef7661c18183da796937569daa1d40c.2592000.1719052312.282335-62795363"

const val grant_type="client_credentials"
const val client_id="KrqQAYIZGA3zSo7CR3zGfiVK"
const val client_secret="lLkRrsqySIOnCFrb1rppSs5RBD308YF9"


class PlantClassifyViewModel(app: Application): AndroidViewModel(application = app){

    private var _plantResult = mutableStateOf<String>("识别植物")
    val plantResult : State<String>
        get()=_plantResult

    //植物图片
    private var _plantImage = mutableStateOf(BitmapFactory.decodeResource(app.resources, R.drawable.recognition))
    val plantImage: State<Bitmap>
        get()=_plantImage

    //植物描述
    private var _plantDescription = mutableStateOf<String>("请点击上方按钮，查询当前识别得出的植物的详细信息")
    val plantDescription: State<String>
        get()=_plantDescription

    //描述按钮是否能被使用
    private var _DescriptionButton = mutableStateOf(false)
    val DescriptionButton: State<Boolean>
        get()=_DescriptionButton

    private var Description:String="未收集成功"


    //获取图像识别结果
    suspend fun getRecognitionResultByImage(bitmap: Bitmap){
        val service:WebService=WebService.service
        _plantImage.value= bitmap
        val encodeResult=BitmapToString(bitmap)
        val result: Response<ResponseBody?>? = service.getFullRecognitionResultByImage(ACCESS_TOKEN,encodeResult,1)
        try {
            val response = result
            if (response != null) {
                _DescriptionButton.value=true
                var resString:String=response.body()!!.string()
                //如果返回值为错误码
                if(isErrorResponse(resString)){
                    //如果错误码显示为Access_Token失效
                    if(extractErrorCode(resString)!=-1){
                        //重新请求Access_Token
                        _plantResult.value="Access Token 失效，正在重新请求"
                        viewModelScope.launch {
                            ACCESS_TOKEN=getPlantToken(getAcessToken(grant_type, client_id, client_secret))
                        }
                        _plantResult.value="请求成功，请重新识别"
                    }else{
                        _plantResult.value="出现其他错误"
                    }
                }
                Description=getPlantDescription(resString)
                _plantResult.value=getPlantResult(resString)
            }else{
                _plantResult.value="no response"
            }
        }catch (e:Exception){
            log(e.toString())
        }
    }

    private suspend fun getAcessToken(grantType:String,client_id:String,client_secret:String):String{
        val service:WebService=WebService.service
        val result: Response<ResponseBody?>? = service.getAccessToken(grantType,client_id,client_secret)
        try {
            val response = result
            if (response != null) {
                val token=response.body()!!.string()
                return token
            }else{
                return "no response"
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return TODO("Provide the return value")
    }

    fun showDesciption(){
        _plantDescription.value=Description
    }


    //判断是否出错
    private fun isErrorResponse(jsonString: String): Boolean {
        try {
            val jsonObject = JSONObject(jsonString)
            // 假设百度API使用error_code字段表示错误
            val errorCode = jsonObject.optInt("error_code", 0) // 使用optInt以防key不存在
            return errorCode != 0 // 如果errorCode不是0，则认为是错误响应
        } catch (e: JSONException) {
            // 如果JSON解析失败，也认为是错误响应
            return true
        }
    }
    //判断错误码是否为AcessToken失效
    private fun extractErrorCode(jsonString: String): Int {
        try {
            val jsonObject = JSONObject(jsonString)
            val errorCode = jsonObject.getInt("error_code")
            return errorCode
        } catch (e: JSONException) {
            // JSON 解析失败或key不存在时返回默认错误码
            return -1
        }
    }


    //已知Access_Token失效，重新获取Access_Token
    private fun getPlantToken(jsonString: String): String {
        val jsonObject = JSONObject(jsonString)
        val accessToken = jsonObject.getString("access_token")
        return accessToken
    }
    //已知百度植物识别结果json字符串的格式，取得识别后最可能的结果
    private fun getPlantResult(jsonString: String):String{

        val jsonObject= JSONObject(jsonString)
        val resultArray=jsonObject.getJSONArray("result")
        if(resultArray.length()>0){
            val firstResult=resultArray.getJSONObject(0)
            return firstResult.getString("name")
        }
        return "未识别成功"
    }
    //已知百度植物识别结果json字符串的格式，取得识别后最可能的结果的植物描述
    private fun getPlantDescription(jsonString: String):String{
        val jsonObject = JSONObject(jsonString)
        val resultArray = jsonObject.getJSONArray("result")
        if (resultArray.length() > 0) {
            val firstResult = resultArray.getJSONObject(0)
            val baikeInfo = firstResult.optJSONObject("baike_info")
            if (baikeInfo != null) {
                log(baikeInfo.getString("description"))
                return baikeInfo.getString("description")
            }
        }
        return "未识别成功"
    }
}