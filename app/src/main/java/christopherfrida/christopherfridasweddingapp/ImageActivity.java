package christopherfrida.christopherfridasweddingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import christopherfrida.christopherfridasweddingapp.InstagramHelpers.BitmapDownloaderTask;

public class ImageActivity extends AppCompatActivity {

	//ZoomImageView imageView;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);

		// Get intent data 
		Intent i = getIntent();
		String url = i.getExtras().getString("url");

		if (url.length() > 0) {

			imageView = (ImageView) findViewById(R.id.full_image_view);

			// start a task to download the image
			BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
			if (!task.searchCache(url))
				task.execute(url);

		} else {


			// display error message

		}
	}
}
