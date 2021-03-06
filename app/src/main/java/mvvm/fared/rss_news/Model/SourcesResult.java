
package mvvm.fared.rss_news.Model;

import java.util.List;
import com.google.gson.annotations.Expose;

public class SourcesResult {

    @Expose
    private List<Article> articles;
    @Expose
    private String status;
    @Expose
    private Long totalResults;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

}
