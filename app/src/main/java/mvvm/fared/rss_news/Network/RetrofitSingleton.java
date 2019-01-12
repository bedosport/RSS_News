package mvvm.fared.rss_news.Network;


import java.util.ArrayList;

import mvvm.fared.rss_news.Model.SourcesResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gabor on 12/3/17.
 */

public final class RetrofitSingleton {
    private static volatile RetrofitSingleton instance = null;
    private ServiceApi client;

    private RetrofitSingleton() {
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).build();
        client = retrofit.create(ServiceApi.class);
    }

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            synchronized(RetrofitSingleton.class) {
                if (instance == null) {
                    instance = new RetrofitSingleton();
                }
            }
        }
        return instance;
    }

    public ServiceApi provideClient() {
        return client;
    }

    public interface ServiceApi {

        @GET("everything")
        Call<SourcesResult> getResults(@Query("q")String q, @Query("apiKey") String apiKey,@Query("page")int page);
        @GET("everything")
        Observable<SourcesResult> getResultsObservable(@Query("q")String q, @Query("apiKey") String apiKey,@Query("page")int page);
    }
}
