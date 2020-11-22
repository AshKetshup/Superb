package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import pdm.di.ubi.pt.superb.FileManager;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.IOException;

public class MessageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String salutationSelected = "";
    String dismissalSelected = "";
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Detail d = FileManager.getDetail();

        final LinearLayout btnBack = (LinearLayout) findViewById(R.id.backButton);
        final LinearLayout btnPreview = (LinearLayout) findViewById(R.id.previewButton);
        EditText messageBody = (EditText) findViewById(R.id.MessageBody_EditText);
        EditText signature = (EditText) findViewById(R.id.MessageSignature_EditText);
        Button sendButton = (Button) findViewById(R.id.SendButton);
        
        Spinner salutationSpinner = (Spinner) findViewById(R.id.salutation_spinner);
        Spinner dismissalSpinner = (Spinner) findViewById(R.id.dismissal_spinner);

        ArrayAdapter salutationAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getSalutation());
        ArrayAdapter dismissalAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getDismissal());

        salutationAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        dismissalAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        salutationSpinner.setAdapter(salutationAdapter);
        dismissalSpinner.setAdapter(dismissalAdapter);

        salutationSpinner.setOnItemSelectedListener(this);
        dismissalSpinner.setOnItemSelectedListener(this);


        btnBack.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               MessageActivity MA = (MessageActivity) v.getContext();
               MA.onBackPressed();
           }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                
                builder.append(salutationSelected).append("\n\n")
                        .append(messageBody.getEditableText().toString()).append("\n\n")
                        .append(signature.getEditableText().toString());

                result = builder.toString();

                Intent intentPreview = new Intent(MessageActivity.this, PreviewActivity.class);
                intentPreview.putExtra("result", result);
                startActivity(intentPreview);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();

                builder.append(salutationSelected).append("!\n")
                        .append(messageBody.getEditableText().toString()).append("\n\n")
                        .append(dismissalSelected).append(".\n")
                        .append(signature.getEditableText().toString());

                result = builder.toString();

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", result);
                startActivity(sendIntent);

                Log.w("Send", result);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idV = parent.getId();
        final int salutationID = R.id.salutation_spinner;
        final int dismissalID = R.id.dismissal_spinner;

        switch (idV) {
            case salutationID:
                salutationSelected = (String) parent.getItemAtPosition(position);
                break;
            case dismissalID:
                dismissalSelected = (String) parent.getItemAtPosition(position);
                break;
        }
        Log.w("MessageActivity", salutationSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}