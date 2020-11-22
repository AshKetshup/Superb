package pdm.di.ubi.pt.superb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView editIcon = (ImageView) findViewById(R.id.editIcon);
        ImageView wrenchIcon = (ImageView) findViewById(R.id.wrenchIcon);
        FloatingActionButton createButton = (FloatingActionButton) findViewById(R.id.createButton);

        changeFragment(editIcon);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(editIcon);
            }
        });

        wrenchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(wrenchIcon);
            }
        });
    }

    private void openDialog() {
        CreateDialogBox createDialog = new CreateDialogBox();
        createDialog.showNow(getSupportFragmentManager(), "Create Dialog");
    }

    public void changeFragment(@NonNull ImageView item) {
        Fragment selectedFragment = null;

        switch (item.getId()) {
            case R.id.editIcon:
                selectedFragment = new EditFragment();
                break;
            case R.id.wrenchIcon:
                selectedFragment = new SettingsFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }
}