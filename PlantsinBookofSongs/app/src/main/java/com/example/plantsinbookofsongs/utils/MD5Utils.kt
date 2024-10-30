package com.example.plantsinbookofsongs.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun md5(text: String): String {
    try {
        val digest = MessageDigest.getInstance("MD5")
        val result = digest.digest(text.toByteArray())
        val sb = StringBuilder()
        result.forEach {
            val number = it.toInt() and 0xff
            val hex = number.toString(16)
            if (hex.length == 1) {
                sb.append("0$hex")
            } else {
                sb.append(hex)
            }
        }
        return sb.toString()
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
        // 通常不需要捕获这个异常，因为它表示Java环境不支持MD5
    }
}