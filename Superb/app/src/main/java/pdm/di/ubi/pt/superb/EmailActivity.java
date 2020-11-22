package pdm.di.ubi.pt.superb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EmailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    String salutationSelected = "";
    String icebreakSelected = "";
    String vowsSelected = "";
    String dismissalSelected = "";
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Detail d = FileManager.getDetail();

        final LinearLayout btnBack = (LinearLayout) findViewById(R.id.backButton);
        final LinearLayout btnPreview = (LinearLayout) findViewById(R.id.previewButton);
        EditText messageBody = (EditText) findViewById(R.id.MessageBody_EditText);
        EditText signature = (EditText) findViewById(R.id.MessageSignature_EditText);
        Button sendButton = (Button) findViewById(R.id.SendButton);

        Spinner salutationSpinner = (Spinner) findViewById(R.id.salutation_spinner);
        Spinner icebreakSpinner = (Spinner) findViewById(R.id.icebreak_spinner);
        Spinner vowsSpinner = (Spinner) findViewById(R.id.vows_spinner);
        Spinner dismissalSpinner = (Spinner) findViewById(R.id.dismissal_spinner);

        ArrayAdapter salutationAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getSalutation());
        ArrayAdapter icebreakAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getIcebreaks());
        ArrayAdapter vowsAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getVows());
        ArrayAdapter dismissalAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, d.getDismissal());

        salutationAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        icebreakAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        vowsAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        dismissalAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        salutationSpinner.setAdapter(salutationAdapter);
        icebreakSpinner.setAdapter(icebreakAdapter);
        vowsSpinner.setAdapter(vowsAdapter);
        dismissalSpinner.setAdapter(dismissalAdapter);

        salutationSpinner.setOnItemSelectedListener(this);
        icebreakSpinner.setOnItemSelectedListener(this);
        vowsSpinner.setOnItemSelectedListener(this);
        dismissalSpinner.setOnItemSelectedListener(this);


        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EmailActivity EA = (EmailActivity) v.getContext();
                EA.onBackPressed();
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();

                builder.append(salutationSelected).append("!\n")
                        .append(icebreakSelected).append(".\n\n")
                        .append(messageBody.getEditableText().toString()).append("\n\n")
                        .append(vowsSelected).append(".\n")
                        .append(dismissalSelected).append(".\n")
                        .append(signature.getEditableText().toString());

                result = builder.toString();

                Intent intentPreview = new Intent(EmailActivity.this, PreviewActivity.class);
                intentPreview.putExtra("result", result);
                startActivity(intentPreview);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();

                builder.append(salutationSelected).append("!\n")
                        .append(icebreakSelected).append(".\n\n")
                        .append(messageBody.getEditableText().toString()).append("\n\n")
                        .append(vowsSelected).append(".\n")
                        .append(dismissalSelected).append(".\n")
                        .append(signature.getEditableText().toString());

                result = builder.toString();

                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Superb");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, result);

                startActivity(emailIntent);

                Log.w("Send", result);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idV = parent.getId();
        final int salutationID = R.id.salutation_spinner;
        final int icebreakID = R.id.icebreak_spinner;
        final int vowsID = R.id.vows_spinner;
        final int dismissalID = R.id.dismissal_spinner;

        switch (idV) {
            case salutationID:
                salutationSelected = (String) parent.getItemAtPosition(position);
                break;
            case icebreakID:
                icebreakSelected = (String) parent.getItemAtPosition(position);
                break;
            case vowsID:
                vowsSelected = (String) parent.getItemAtPosition(position);
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