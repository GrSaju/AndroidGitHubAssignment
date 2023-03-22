package com.example.githubassignment.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

//    private val httpClient: OkHttpClient by lazy {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
//
//        OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
//            .build()
//    }

    private val okClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Bearer", " 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")
//                        .header("Authorization", " eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsifQ.eyJpc3MiOiJodHRwczovL215dGNhcmVhaS5iMmNsb2dpbi5jb20vMmY2NWE4NGItMzE5NS00YjNkLWEzYjctNTQ1N2E0NzFlMDVjL3YyLjAvIiwiZXhwIjoxNjc1MzA3NDYxLCJuYmYiOjE2NzUyMjEwNjEsImF1ZCI6IjA5MzFmNTgwLTMxNjgtNDI3Yy1iYzA4LWJlZmMwMDc4MTlhZCIsIm9pZCI6IjNiNGM3YzViLTExMDgtNDFlMy04YzU2LWJlZDg1ZjZiMjhlMiIsInN1YiI6IjNiNGM3YzViLTExMDgtNDFlMy04YzU2LWJlZDg1ZjZiMjhlMiIsIm5hbWUiOiJTYWp1IEdSIiwiZ2l2ZW5fbmFtZSI6IlNhanUiLCJmYW1pbHlfbmFtZSI6IkdSIiwiZW1haWxzIjpbInNhanUuZ3JAdGNhcmUuYWkiXSwidGZwIjoiQjJDXzFfY29uc3VtZXJfc2lnbl9pbiIsInNjcCI6InVzZXIucmVhZCIsImF6cCI6IjA5MzFmNTgwLTMxNjgtNDI3Yy1iYzA4LWJlZmMwMDc4MTlhZCIsInZlciI6IjEuMCIsImlhdCI6MTY3NTIyMTA2MX0.OCVdZguEuWlkZMQ9xa1uPOdjyWacpqyBCLn_qUK9qqgrFAzILsYwKlAmTiW2M7d84ql-FzCjPhBz7GEcemX02ojLfFCuY-C8xvAhgWN2TlIMBxhAHlsKEJdHnuha6HrbdfeOXhqO-VQunfQs2vOi3zzKjsJdfLFvrbOe60yZ9lPoBADHwL-B02nxLL0OWTdc2QEfaLM5mlJgUemiM_sHVWkK7reTgpjn8xirs7vzmHGrpTu68-2CE_H8Jnk-JBhBzKU-wGgUCNVNLK0UGhpfWxqwFaVasj2rRGA4-TulKuMRUAYfRoft7FXRiECHUFUkcoJPXEWSk1QQ-N_wbd-ToA")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(APIUrls.domainURL)
            .client(okClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

}