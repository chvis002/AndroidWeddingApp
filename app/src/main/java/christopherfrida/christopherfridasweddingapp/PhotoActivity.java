package christopherfrida.christopherfridasweddingapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import org.json.JSONObject;

import christopherfrida.christopherfridasweddingapp.InstagramHelpers.ImageStreamAdapter;
import christopherfrida.christopherfridasweddingapp.InstagramHelpers.WebInterface;

public class PhotoActivity extends AppCompatActivity {

    private JSONObject imageData;
    private ListView listView;

    private static final String HASHTAG = "nofilter";
    private static final String FETCH_COUNT_PARAM = "&count=";
    private static final String MIN_IMAGE_ID_PARAM = "&min_id=";
    private static final String MAX_IMAGE_ID_PARAM = "&max_id=";

    private String CLIENT_ID = "";
    private String USER_ID = "";
    private static int COUNT = 2;

    protected int min_image_id = 0;
    protected int max_image_id;



    //change tag from nofilter to something else
    //private static final String FETCH_URL = "https://api.instagram.com/v1/tags/nofilter/media/recent?client_id=";
    private String FETCH_URL = "";


    private static int TILE_WIDTH = 500;
    int number = 0;
    RequestImagesTask request;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        CLIENT_ID = getString(R.string.instagram_client_id);
        USER_ID = getString(R.string.instagram_chrvi915_id);
        FETCH_URL = "https://api.instagram.com/v1/users/"+USER_ID+"/media/recent/?client_id=";

        listView = (ListView) findViewById(R.id.image_list_view);

        String requestURL = FETCH_URL+CLIENT_ID+FETCH_COUNT_PARAM+COUNT;
        Log.d("requestURL",requestURL);

        request = new RequestImagesTask(
        //        "https://api.instagram.com/v1/tags/nofilter/media/recent?client_id=00dde0c83c964baf98d5d9a00367855d&count=10",
        //        "https://api.instagram.com/v1/users/181858847/media/recent/?client_id=00dde0c83c964baf98d5d9a00367855d&count=5",
                requestURL,
                this);
        request.execute();

        request = new RequestImagesTask("https://api.instagram.com/v1/users/181858847/media/recent/?client_id=00dde0c83c964baf98d5d9a00367855d&count=5", this)
        request.execute();
        
        contexto = this;

        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //listView.setNumColumns(metrics.widthPixels / TILE_WIDTH);

        //this doesn't work
        /*
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(PhotoActivity.this, ImageActivity.class);

                try {

                    String url = imageData.getJSONArray("data")
                            .getJSONObject(position).getJSONObject("images")
                            .getJSONObject("standard_resolution")
                            .getString("url");
                    Log.d(url, url);
                    i.putExtra("url", url);
                } catch (JSONException e) {

                    i.putExtra("url", "");
                }

                startActivity(i);
            }
        });*/

         listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

    }

    public void fetchMore(View view) {
        //fetch more
    }

    private class RequestImagesTask extends AsyncTask<Void, Void, Void> {
        private String url;
        private Context c;

        public RequestImagesTask(String url, Context c) {
            super();
            this.url = url;
            this.c = c;
        }

        @Override
        protected Void doInBackground(Void... params) {
            imageData = WebInterface.requestWebService(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
             listView.setAdapter(new ImageStreamAdapter(c, imageData));
        }

    }

    public void startInstagram(View v) {

        String type = "image/*";
        //String filename = "/myPhoto.jpg";
        //String mediaPath = Environment.getExternalStorageDirectory() + filename;


        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        //File media = new File(mediaPath);
        //Uri uri = Uri.fromFile(media);

        // Add the URI and the caption to the Intent.
        //    share.putExtra(Intent.EXTRA_STREAM, uri);
        //share.putExtra(Intent.EXTRA_TEXT, HASHTAG);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));



    }

}