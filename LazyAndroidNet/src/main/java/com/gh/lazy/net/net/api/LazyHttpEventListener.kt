package com.gh.lazy.net.net.api

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import okhttp3.Call
import okhttp3.Connection
import okhttp3.EventListener
import okhttp3.EventListener.Factory
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.atomic.AtomicLong

/**
 * @author GaoHua
 */
class LazyHttpEventListener(private var callId: AtomicLong, private var callStartNanos: Long) :
    EventListener() {

    private fun printHttpEvent(eventName: String) {
        val elapsedNanos: Long = System.currentTimeMillis()

        Log.d(
            "HttpEventListener", "[--------------------".plus(eventName).plus("-------------------]")
                .plus("\n")
                .plus("call id:")
                .plus(callId)
                .plus("\n")
                .plus("time:")
                .plus((elapsedNanos-callStartNanos))
                .plus("ms")
        )
    }

    companion object {
        private val nextCallId = AtomicLong(1L)

        val FACTORY = Factory { call ->
            nextCallId.getAndIncrement()
            Log.d(
                "HttpEventListener",("requestUrl is:")
                    .plus(call.request().url())
                    .plus("\n")
            )
            return@Factory LazyHttpEventListener(nextCallId, System.currentTimeMillis())
        }
    }

    override fun callEnd(call: Call) {
        printHttpEvent("callEnd")
    }

    override fun callFailed(call: Call, ioe: IOException) {
        printHttpEvent("callFailed")
    }

    override fun callStart(call: Call) {
        printHttpEvent("callStart")
    }

    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        printHttpEvent("connectEnd")
    }

    override fun connectFailed(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?,
        ioe: IOException
    ) {
        printHttpEvent("connectFailed")
    }

    override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {
        printHttpEvent("connectStart")
    }

    override fun connectionAcquired(call: Call, connection: Connection) {
        printHttpEvent("connectionAcquired")
    }

    override fun connectionReleased(call: Call, connection: Connection) {
        printHttpEvent("connectionReleased")
    }

    override fun dnsEnd(call: Call, domainName: String, inetAddressList: List<InetAddress>) {
        printHttpEvent("dnsEnd")
    }

    override fun dnsStart(call: Call, domainName: String) {
        printHttpEvent("dnsStart")
    }

    override fun requestBodyEnd(call: Call, byteCount: Long) {
        printHttpEvent("requestBodyEnd")
    }

    override fun requestBodyStart(call: Call) {
        printHttpEvent("requestBodyStart")
    }


    override fun requestHeadersEnd(call: Call, request: Request) {
        printHttpEvent("requestHeadersEnd")
    }

    override fun requestHeadersStart(call: Call) {
        printHttpEvent("requestHeadersStart")
    }

    override fun responseBodyEnd(call: Call, byteCount: Long) {
        printHttpEvent("responseBodyEnd")
    }

    override fun responseBodyStart(call: Call) {
        printHttpEvent("responseBodyStart")
    }


    override fun responseHeadersEnd(call: Call, response: Response) {
        printHttpEvent("responseHeadersEnd")
    }

    override fun responseHeadersStart(call: Call) {
        printHttpEvent("responseHeadersStart")
    }
}