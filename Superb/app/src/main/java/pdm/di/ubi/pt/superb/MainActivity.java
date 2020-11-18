package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] EEH = {0};
        setContentView(R.layout.activity_main);

        final Button btnClose = (Button) findViewById(R.id.BtnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btnClose.getText().equals("Close")) {
                    finish();
                    System.exit(0); // A sair da app
                }
                else if (btnClose.getText().equals("Go away, I hate you.")) {
                    String id = "dQw4w9WgXcQ";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + id));
                    startActivity(intent);
                }
            }
        }
        );

        final Button btnStart = (Button) findViewById(R.id.BtnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (EEH[0] != 0) {
                    EEH[0] = 0;
                } else {
                    Intent intent = new Intent(MainActivity.this, StartActivity.class);
                    startActivity(intent);
                }
            }
        }
        );

        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setClickable(true);
        logo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EEH[0] += 1;
                if (EEH[0] == 10) {
                    btnClose.setText("Go away, I hate you.");
                }
                if (EEH[0] > 10) {
                    btnClose.setText("Close");
                    EEH[0] = 0;
                }
            }
        });
    }
}