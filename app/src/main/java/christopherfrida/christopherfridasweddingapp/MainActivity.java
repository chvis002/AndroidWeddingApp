package christopherfrida.christopherfridasweddingapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> links;
    private ArrayAdapter<String> linksAdapter;
    private ListView lvLinks;

    public final static int EMAIL_SENT_RESULT_CODE = 90;

    private ScoreDbAdapter scoreDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreDbAdapter = new ScoreDbAdapter(this);
        // scoreDbAdapter.open(); //needed?

        lvLinks = (ListView) findViewById(R.id.listView);

        // All links
        links = new ArrayList<String>();
        links.add(getString(R.string.title_activity_information));
        links.add(getString(R.string.title_activity_directions_map));
        links.add("Bilder");
        links.add(getString(R.string.title_activity_quiz));
        links.add(getString(R.string.title_activity_contact_us));
        links.add("avsluta");

        linksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, links);
        lvLinks.setAdapter(linksAdapter);

        setupListViewListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    protected void startInformationView(){
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }
    protected void startDirectionsMap(){
        Intent intent = new Intent(this, MapFragment.class);
        startActivity(intent);
    }

    protected void startContactUs() {
        Intent intent = new Intent(this, ContactUsActivity.class);
        startActivityForResult(intent, EMAIL_SENT_RESULT_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case MainActivity.EMAIL_SENT_RESULT_CODE:
                if (resultCode == RESULT_OK){
                    DialogFragment message = new ThanksDialogFragment();
                    message.show(getFragmentManager(),"thanks");
                }
                break;
        }
    }



    protected void startImages() {



    }

    private void setupListViewListener() {

        lvLinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {


                Log.d("setOnITemClickListner", "Pos: "+pos+" id: "+id+"View"+item.toString());
                Log.i("setOnITemClickListner", "Pos: "+pos+" id: "+id+"View"+item.toString());
                switch (pos){//Todo dependent on pos start correct activity
                    case 0:
                        startInformationView(); //can't create Intents inside another class...
                        break;//starta Information
                    case 1:
                        startDirectionsMap();
                        break;//starta directions
                    case 2:
                        startImages();
                        break;//starta Bilder from instagram
                    case 3: break;//quiz
                    case 4:
                        startContactUs();
                    //Skicka mail till mailadress

                        break;
                    case 5:
                        finish();
                }



            }
        });

    }
}
