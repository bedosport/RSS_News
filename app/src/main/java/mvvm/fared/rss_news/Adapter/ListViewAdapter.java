package mvvm.fared.rss_news.Adapter;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mvvm.fared.rss_news.Model.Article;
import mvvm.fared.rss_news.databinding.SourceItemBinding;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private final ArrayList<Article> articles = new ArrayList<>();

    public ListViewAdapter() {}

    public void add(Article article) {
        articles.add(article);
        this.notifyItemInserted(articles.size() - 1);
    }

    public void addAll(List<Article> articles) {
        this.articles.addAll(articles);
        this.notifyItemInserted(this.articles.size() - 1);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public @NonNull
    ListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SourceItemBinding binding = SourceItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SourceItemBinding binding;
        ViewHolder (SourceItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
        ViewHolder(View view) {
            super(view);
        }
        void bind(@NonNull Article article) {
            binding.setArticle(article);
            binding.executePendingBindings();
        }
    }
}
