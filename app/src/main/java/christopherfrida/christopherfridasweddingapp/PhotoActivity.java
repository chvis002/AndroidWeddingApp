package christopherfrida.christopherfridasweddingapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.GridView;

import org.json.JSONObject;

import christopherfrida.christopherfridasweddingapp.InstagramHelpers.ImageStreamAdapter;
import christopherfrida.christopherfridasweddingapp.InstagramHelpers.WebInterface;

public class PhotoActivity extends AppCompatActivity {

    private JSONObject imageData;
    private GridView gridView;
    private static int TILE_WIDTH = 220;
    int number = 0;
    RequestImagesTask request;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        gridView = (GridView) findViewById(R.id.image_grid_view);

        request = new RequestImagesTask(
                "https://api.instagram.com/v1/tags/nofilter/media/recent?client_id=00dde0c83c964baf98d5d9a00367855d&count=5",
                this);
        request.execute();

        contexto = this;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gridView.setNumColumns(metrics.widthPixels / TILE_WIDTH);

        /*gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent i = new Intent(this, ImageActivity.class);

                try {

                    String url = imageData.getJSONArray("data")
                            .getJSONObject(position).getJSONObject("images")
                            .getJSONObject("standard_resolution")
                            .getString("url");
                    i.putExtra("url", url);
                } catch (JSONException e) {
                    i.putExtra("url", "");
                }

                startActivity(i);
            }
        });
        gridView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        }); */

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
            gridView.setAdapter(new ImageStreamAdapter(c, imageData));
        }

    }

}