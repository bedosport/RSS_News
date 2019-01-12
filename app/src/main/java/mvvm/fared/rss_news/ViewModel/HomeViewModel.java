package mvvm.fared.rss_news.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import mvvm.fared.rss_news.Model.Article;
import mvvm.fared.rss_news.Network.RetrofitSingleton;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {
    private Application application;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public Observable<Article> loadNextDataFromApi(int offset) {
        Log.e("The page number", offset+"");
        return RetrofitSingleton.getInstance().provideClient()
                .getResultsObservable("bitcoin","dec3c7d9eff540c688dc87c2e80411b6",offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(sourcesResult -> {
                    return Observable.from(sourcesResult.getArticles());
                })
                .flatMap(Observable::just);
    }

}
