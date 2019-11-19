//package com.example.tutor4you;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//import static com.example.tutor4you.Constants.YELP_API_KEY;
//import static com.example.tutor4you.Constants.YELP_BASE_URL;
//
//public class LinkedInClient {
//
//    private static Retrofit retrofit = null;
//
//    public static LinkedApi getClient() {
//
//        if (retrofit == null) {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request newRequest  = chain.request().newBuilder()
//                                    .addHeader("Authorization", YELP_API_KEY)
//                                    .build();
//                            return chain.proceed(newRequest);
//                        }
//                    })
//                    .build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(YELP_BASE_URL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return retrofit.create(Linkedi.class);
//    }
//
//
//}
