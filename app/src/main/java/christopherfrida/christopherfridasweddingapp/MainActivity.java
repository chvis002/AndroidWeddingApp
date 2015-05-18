package christopherfrida.christopherfridasweddingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> links;
    private ArrayAdapter<String> linksAdapter;
    private ListView lvLinks;

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
        links.add("Information");
        links.add("Vägbeskrivning");
        links.add("Bilder");
        links.add("Frågespel");

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void startInformationView(){
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
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
                    case 1: break;//starta Vägbeskrivning
                    case 2: break;//starta Bilder från instagram
                    case 3: break;//Frågespel
                }



            }
        });

    }
}
