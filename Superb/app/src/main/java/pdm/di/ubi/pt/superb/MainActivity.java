package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] EEH = {0};
        setContentView(R.layout.activity_main);

        final Button btnClose = (Button) findViewById(R.id.BtnClose);
        final Button btnStart = (Button) findViewById(R.id.BtnStart);


        // check and copy nonexistent files
        if (!(new File("/data/data/pdm.di.ubi.pt.superb", "Salutations.ash").exists())) {
            createFile("Salutations.ash");
        }
        if (!(new File("/data/data/pdm.di.ubi.pt.superb", "IceBreaks.ash").exists())) {
            createFile("IceBreaks.ash");
        }
        if (!(new File("/data/data/pdm.di.ubi.pt.superb", "Vows.ash").exists())) {
            createFile("Vows.ash");
        }
        if (!(new File("/data/data/pdm.di.ubi.pt.superb", "Dismissals.ash").exists())) {
            createFile("Dismissals.ash");
        }


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

            // /data/data/pdm.di.ubi.pt.superb
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

    private void createFile(String fileName) {
        try {
            File myObj = new File("/data/data/pdm.di.ubi.pt.superb", fileName);
            if (myObj.createNewFile()) {
                Log.w("MainActivity", "Created File: " + myObj.getName());
                writeToFile(fileName);
            } else {
                Log.w("MainActivity", "File already exists");
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error:" + e);
        }
    }

    private void writeToFile(String fileName) {
        try {
            String toFile = "";

            FileWriter myWriter = new FileWriter("/data/data/pdm.di.ubi.pt.superb/" + fileName);
            if (fileName.equals("Salutations.ash")) {
                toFile = "Olá$Boa tarde$Bom dia$Boa noite$";
            } else if (fileName.equals("IceBreaks.ash")) {
                toFile = "Espero que este email o encontre bem.$";
            } else if (fileName.equals("Vows.ash")) {
                toFile = "Votos de boa continuação$";
            } else if (fileName.equals("Dismissals.ash")) {
                toFile = "Cumprimentos$";
            }

            myWriter.write(toFile);
            myWriter.close();
        } catch(IOException e) {
            Log.e("MainActivity", e.getMessage());
        }
    }

}