package com.example.stuart.crawl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CrawlsCardAdapter extends RecyclerView.Adapter<CrawlsCardAdapter.ViewHolder>{

  private List<String> dataSet;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  public CrawlsCardAdapter(List<String> dataSet) {
    this.dataSet = dataSet;
  }

  @Override
  public CrawlsCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //create a new view
    TextView v = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.crawls_text_view, parent, false);
    //TODO: set view's parameters
    ViewHolder vh = new ViewHolder(v);
    return vh;
  }

  @Override
  public void onBindViewHolder(CrawlsCardAdapter.ViewHolder holder, int position) {
    holder.textView.setText(dataSet.get(position));
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }
}
