package com.example.stuart.crawl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class NewCrawlActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_crawl);

    Button doneButton = findViewById(R.id.doneButton);
    final EditText startInput = findViewById(R.id.start_text);
    final EditText endInput = findViewById(R.id.end_text);

    doneButton.setOnClickListener(
        new View.OnClickListener()
        {
          public void onClick(View view)
          {
            Log.v("sfgarg", startInput.getText().toString());
          }
        });
  }
}
