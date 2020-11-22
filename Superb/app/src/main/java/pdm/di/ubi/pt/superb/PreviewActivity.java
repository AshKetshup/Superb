package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        LinearLayout btnBack = (LinearLayout) findViewById(R.id.backButton);

        TextView textpreview = (TextView) findViewById(R.id.TextPreview);

        Intent i = getIntent();
        String result = i.getStringExtra("result");

        textpreview.setText(result);


        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PreviewActivity PA = (PreviewActivity) v.getContext();
                PA.onBackPressed();
            }
        });


    }
}