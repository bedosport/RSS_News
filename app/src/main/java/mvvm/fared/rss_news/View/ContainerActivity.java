package mvvm.fared.rss_news.View;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import mvvm.fared.rss_news.Adapter.ListViewAdapter;
import mvvm.fared.rss_news.Model.Article;
import mvvm.fared.rss_news.Model.EndlessRecyclerViewScrollListener;
import mvvm.fared.rss_news.R;
import mvvm.fared.rss_news.ViewModel.HomeViewModel;
import mvvm.fared.rss_news.databinding.SourceItemBinding;
import rx.subscriptions.CompositeSubscription;

public class ContainerActivity extends AppCompatActivity {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private ListViewAdapter adapter;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        RecyclerView listView = findViewById(R.id.my_list);
        listView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        ProgressDialog dialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);
        dialog.cancel();
        listView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        subscriptions.add(viewModel.loadNextDataFromApi(page)
                                .doOnSubscribe(dialog::show)
                                .doOnNext(article -> {
                                    adapter.add(article);
                                })
                                .doOnCompleted(dialog::cancel).subscribe());
                    }
                },1500);

            }
        });
        subscriptions.add(viewModel.loadNextDataFromApi(1).subscribe(this::onResponse, this::onFailure));
    }

    private void onResponse(Article article) {
        adapter.add(article);
    }

    private void onFailure(Throwable t) {
        Toast.makeText(this, "Network error: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        subscriptions.unsubscribe();
        super.onDestroy();
    }

}
