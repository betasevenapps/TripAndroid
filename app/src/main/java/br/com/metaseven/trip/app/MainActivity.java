package br.com.metaseven.trip.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

import br.com.metaseven.trip.app.app.App;
import br.com.metaseven.trip.app.uihelper.MainUiHelper;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;


public class MainActivity extends Activity {

    private static final int TOTAL_BACKGROUND = 4;
    private App app;
    private PullToRefreshAttacher attacher;
    private MainUiHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        app = (App) getApplication();
        attacher = PullToRefreshAttacher.get(this);
        uiHelper = new MainUiHelper(getWindow().getDecorView().findViewById(android.R.id.content));
        uiHelper.facebook.setOnClickListener(configurarOnFacebookClickListener());
        configurarBackground();
    }

    private void configurarBackground() {
        try {
            Random r = new Random();
            InputStream is = getAssets().open("login/" + r.nextInt(TOTAL_BACKGROUND) + ".jpg");
            uiHelper.background.setImageBitmap(BitmapFactory.decodeStream(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener configurarOnFacebookClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app.isInternetConnection()){
                    ParseFacebookUtils.logIn(
                            Arrays.asList(ParseFacebookUtils.Permissions.User.ABOUT_ME, ParseFacebookUtils.Permissions.User.BIRTHDAY),
                            MainActivity.this, callBackLoginFacebook());
                    attacher.setRefreshing(true);
                }
            }
        };
    }

    private LogInCallback callBackLoginFacebook() {
        return new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException error) {
                if(error == null){
                    if (user != null) {
                        ParseInstallation parseInstalation = ParseInstallation.getCurrentInstallation();
                        parseInstalation.put("user", user);
                        parseInstalation.saveEventually();
                        navegar();
                    }
                }else{
                    //Exibir mensagem de erro
                }
                attacher.setRefreshComplete();
            }
        };
    }

    private void navegar() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }
}
