
package mvvm.fared.rss_news.Model;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.annotations.Expose;

import mvvm.fared.rss_news.View.ShowWebSourceActivity;

public class Article {

    @Expose
    private String author;
    @Expose
    private String content;
    @Expose
    private String description;
    @Expose
    private String publishedAt;
    @Expose
    private Source source;
    @Expose
    private String title;
    @Expose
    private String url;
    @Expose
    private String urlToImage;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void onItemClick(View view,Article article){
        Intent intent1=new Intent(view.getContext(),ShowWebSourceActivity.class);
        intent1.putExtra("url",article.getUrl());

        view.getContext().startActivity(intent1);
    }

}
