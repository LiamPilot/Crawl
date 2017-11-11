package com.example.stuart.crawl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CrawlsActivity extends AppCompatActivity {

  private RecyclerView crawlsRecyclerView;
  private RecyclerView.Adapter crawlsAdapter;
  private RecyclerView.LayoutManager crawlsLayoutManager;

  private LinkedList<String> crawlsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crawls);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    crawlsList = new LinkedList<>();

    crawlsRecyclerView = (RecyclerView) findViewById(R.id.crawls_recycler_view);
    crawlsRecyclerView.setHasFixedSize(true);

    crawlsLayoutManager = new LinearLayoutManager(this);
    crawlsRecyclerView.setLayoutManager(crawlsLayoutManager);

    crawlsAdapter = new CrawlsCardAdapter(crawlsList);
    crawlsRecyclerView.setAdapter(crawlsAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.menu_crawls, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void onNewCrawlBtnClick(View view) {
    crawlsList.addFirst("The Cock and Bollocks");
    crawlsAdapter.notifyDataSetChanged();
  }
}
