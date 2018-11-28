package cn.white.ymc.todomaster.model

import cn.white.ymc.todomaster.utils.LoggerE
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit

/**
 *  okhttp log 监听器
 *
 * @packageName: cn.white.ymc.todomaster.model.api
 * @fileName: HttpLoggingInterceptor
 * @date: 2018/10/8  13:16
 * @author: ymc
 * @QQ:745612618
 */

class HttpLoggingInterceptor : Interceptor {

    private val charsetUTF8 = Charset.forName("UTF-8")

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val requestBody = request.body()
        var body: String? = null
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset: Charset? = charsetUTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(charsetUTF8)
            }
            body = buffer.readString(charset!!)
        }
        LoggerE("发送请求: method：" + request.method()
                + "\nurl：" + request.url()
                + "\n请求头：" + request.headers()
                + "\n请求参数: " + body)
        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body()
        var  rBody: String ?

        val source = responseBody!!.source()
        source.request(java.lang.Long.MAX_VALUE)
        val buffer = source.buffer()

        var charset: Charset? = charsetUTF8
        val contentType = responseBody.contentType()
        if (contentType != null) {
            try {
                charset = contentType.charset(charsetUTF8)
            } catch (e: UnsupportedCharsetException) {
                e.printStackTrace()
            }
        }
        rBody = buffer.clone().readString(charset!!)

        LoggerE("收到响应: code:" + response.code()
                + "\n请求url：" + response.request().url()
                + "\n请求body：" + body
                + "\nResponse: " + rBody
                + "\n 请求时间：" + tookMs)

        return response
    }


}
