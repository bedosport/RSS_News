package mvvm.fared.rss_news.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import mvvm.fared.rss_news.R;


public class ShowWebSourceActivity extends AppCompatActivity {
    WebView myWebView;
    Toolbar toolbar;
    ProgressBar loadingProgressBar;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web_source);
        toolbar=findViewById(R.id.my_toolbar);
        getSupportActionBar().hide();
        myWebView=findViewById(R.id.my_web_source);
        loadingProgressBar=(ProgressBar)findViewById(R.id.progressbar_Horizontal);
        Intent intent=getIntent();
        myWebView.setWebViewClient(new MyBrowser(loadingProgressBar));
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        myWebView.loadUrl(intent.getStringExtra("url"));
        //toolbar.setTitle(intent.getStringExtra("name"));
        loadingProgressBar.setProgress(0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private class MyBrowser extends WebViewClient {
        private ProgressBar progressBar;

        public MyBrowser(ProgressBar progressBar) {
            this.progressBar=progressBar;
            toolbar.setTitle("Loading...");
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            toolbar.setTitle(R.string.app_name);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            progressBar.setProgress(progressBar.getProgress()+1);

        }
    }
    public void setValue(int progress) {
        this.loadingProgressBar.setProgress(progress);
    }
}
