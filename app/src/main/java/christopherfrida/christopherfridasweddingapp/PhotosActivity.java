package christopherfrida.christopherfridasweddingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import christopherfrida.christopherfridasweddingapp.InstagramHelpers.Instagram;
import christopherfrida.christopherfridasweddingapp.InstagramHelpers.InstagramSession;
import christopherfrida.christopherfridasweddingapp.InstagramHelpers.InstagramUser;


public class PhotosActivity extends AppCompatActivity {

    private InstagramSession mInstagramSession;
    private Instagram mInstagram;
    private InstagramUser myInstagramUser;
    public final static String INSTAGRAM_HASHTAG = "#ChristopherFrida2015";

    private ProgressBar mLoadingPb;
    private GridView mGridView;

    private final String CLIENT_ID = getString(R.string.instagram_client_id);
    private final String CLIENT_SECRET = getString(R.string.instagram_client_secret);
    private final String REDIRECT_URI = getString(R.string.instagram_redirect_uri);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_photos);

        mInstagram  		= new Instagram(this, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI);
        myInstagramUser = new InstagramUser();
        myInstagramUser.accessToken = getString(R.string.instagram_access_token);
        myInstagramUser.username ="chrvi915";

        if (mInstagramSession.isActive()) {
            setContentView(R.layout.activity_user);

            InstagramUser instagramUser = mInstagramSession.getUser();

            mLoadingPb = (ProgressBar) findViewById(R.id.pb_loading);
            mGridView = (GridView) findViewById(R.id.gridView);

            ((TextView) findViewById(R.id.tv_name)).setText(instagramUser.fullName);
            ((TextView) findViewById(R.id.tv_username)).setText(instagramUser.username);

            ((Button) findViewById(R.id.btn_logout)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    mInstagramSession.reset();

                    startActivity(new Intent(PhotosActivity.this, PhotosActivity.class));

                    finish();
                }
            });
        } else {
            setContentView(R.layout.activity_main);

            ((Button) findViewById(R.id.btn_connect)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    mInstagram.authorize(mAuthListener);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
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


    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(InstagramUser user) {
            finish();

            startActivity(new Intent(PhotosActivity.this, PhotosActivity.class));
        }

        @Override
        public void onError(String error) {
            showToast(error);
        }

        public void onCancel(){

        }
    };


}
