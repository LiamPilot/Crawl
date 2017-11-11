package com.example.stuart.crawl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class CrawlsCardViewAdapter extends RecyclerView
.Adapter<CrawlsCardViewAdapter
.CrawlCardObjectHolder>{

  private LinkedList<CrawlCardObject> dataSet;

  public CrawlsCardViewAdapter(LinkedList<CrawlCardObject> dataSet) {
    this.dataSet = dataSet;
  }

  @Override
  public CrawlCardObjectHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.crawls_card_view, parent, false);

    CrawlCardObjectHolder cardObjectHolder = new CrawlCardObjectHolder(view);
    return cardObjectHolder;
  }

  @Override
  public void onBindViewHolder(CrawlCardObjectHolder holder, int position) {
    holder.label.setText(dataSet.get(position).getCrawlName());
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  public static class CrawlCardObjectHolder extends RecyclerView.ViewHolder {
    public TextView label;

    public CrawlCardObjectHolder(View itemView) {
      super(itemView);
      label = (TextView) itemView.findViewById(R.id.crawlName);
    }
  }


}
