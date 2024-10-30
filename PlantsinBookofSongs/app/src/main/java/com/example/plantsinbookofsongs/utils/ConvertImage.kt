package com.example.plantsinbookofsongs.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class ConvertImage {
    companion object{
        //未降低分辨率，导致报错

        /*fun BitmapToString(bitmap: Bitmap):String{
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }*/

        //太大的图片，生成的Base64编码字符串可能很大，超出字段存储容量的上限
        //因此，要适当降低分辨率，以减少生成的字符串长度
        fun resizeImage(bitmap: Bitmap, coefficient: Double): String? {
            //按照指定的因数，对图片进行缩放，调整其分辨率，得到一个“较小”的Bitmap对象
            val resizedBitmap = Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width * coefficient).toInt(),
                (bitmap.height * coefficient).toInt(),
                true
            )
            //按照PNG格式对图片进行压缩
            val newStream = ByteArrayOutputStream();
            val resultCompress = resizedBitmap.compress(
                Bitmap.CompressFormat.PNG,
                100, newStream)
            //压缩成功，则对二进制数据进行Base64编码
            if (resultCompress) {
                val newByteArray = newStream.toByteArray()
                return Base64.encodeToString(newByteArray, Base64.DEFAULT)
            }
            //压缩不成功，返回null
            return null
        }
        fun BitmapToString(bitmap: Bitmap): String? {
            val stream = ByteArrayOutputStream()
            val resultCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            if (resultCompress) {
                val byteArray = stream.toByteArray()
                //检查图片文件大小，给与不同的缩放比率，得到Base64编码
                val imageAsString = if (byteArray.size > 200_0000) {
                    resizeImage(bitmap, 0.1)
                } else if (byteArray.size in 100_0000..200_0000) {
                    resizeImage(bitmap, 0.5)
                } else {
                    Base64.encodeToString(byteArray, Base64.DEFAULT)
                }
                return imageAsString
            }
            return null
        }
    }
}