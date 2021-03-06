package christopherfrida.christopherfridasweddingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ContactUsActivity extends AppCompatActivity {

    private EditText title;
    private EditText message;

    private Button send_button;

    private static String[] EMAIL_ADDRESS = {"chvis002@gmail.com"};
    private static String[] EMAIL_CC_ADDRESS = {"fripe206@gmail.com"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        title = (EditText) findViewById(R.id.title_ET);
        message = (EditText) findViewById(R.id.message_ET);

        /*
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ViewGroup.LayoutParams vg_lp = view.getLayoutParams();

                // Make sure we are in RelativeLayout
                if (vg_lp instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
                    rl_lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                    view.setLayoutParams(rl_lp);
                    Log.d("click", "click");
                }
            }
        });
        */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_us, menu);
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


    public void sendMessage(View v) {

        String titleText = title.getText().toString();
        String messageText = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("plain/text");
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL_ADDRESS);
//        intent.putExtra(Intent.EXTRA_CC,EMAIL_CC_ADDRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT,titleText);
        intent.putExtra(Intent.EXTRA_TEXT, messageText);

        if (intent.resolveActivity(getPackageManager()) != null){
            Log.d(" sendMessage(View v)", "success!");
            //startActivity(intent);
            startActivityForResult(Intent.createChooser(intent, "Skicka meddelande med:"), MainActivity.EMAIL_SENT_RESULT_CODE);

            Intent returnIntent = new Intent();
            /* If you want to send message with the tag result */
            // returnIntent.putExtra("result","success");
            setResult(RESULT_OK,returnIntent);

            finish();


        }
        else {
            Log.d(" sendMessage(View v)", "fail");
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish();
        }
    }

}
