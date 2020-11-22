package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import pdm.di.ubi.pt.superb.FileManager;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Detail d = FileManager.getDetail();

        Log.i("MessageActivity: ", d.getSalutation().toString());

    }
}