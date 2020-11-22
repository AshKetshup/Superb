package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Detail d = FileManager.getDetail();

        setContentView(R.layout.activity_email);

        Log.i("EmailActivity: ", d.getSalutation().toString());
    }
}