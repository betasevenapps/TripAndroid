package br.com.metaseven.trip.app.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import br.com.metaseven.trip.app.callback.Callback;

/**
 * Created by vagnnermartins on 16/09/14.
 */
public class FindProfilePictureFacebookTask extends AsyncTask<Void, Void, Bitmap>{

    private final String facebookId;
    private final Callback callback;
    private Exception exception;

    public FindProfilePictureFacebookTask(String facebookId, Callback callback){
        this.facebookId = facebookId;
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap result = null;
        String url = String.format("https://graph.facebook.com/%s/picture?type=large",
                facebookId);
        try {
            InputStream inputStream = new URL(url).openStream();
            result = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(exception == null){
            callback.onReturn(null, bitmap);
        }else{
            callback.onReturn(exception);
        }
    }
}
