package br.com.metaseven.trip.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

import br.com.metaseven.trip.app.R;
import br.com.metaseven.trip.app.app.App;
import br.com.metaseven.trip.app.asynctask.FindProfilePictureFacebookTask;
import br.com.metaseven.trip.app.callback.Callback;
import br.com.metaseven.trip.app.enums.StatusEnum;
import br.com.metaseven.trip.app.parse.UserParse;
import br.com.metaseven.trip.app.service.UserParseService;
import br.com.metaseven.trip.app.uihelper.MainUiHelper;
import br.com.metaseven.trip.app.util.NavegacaoUtil;
import br.com.metaseven.trip.app.util.TripUtil;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;


public class MainActivity extends Activity {

    private static final int TOTAL_BACKGROUND = 4;

    private App app;
    private PullToRefreshAttacher attacher;
    private MainUiHelper uiHelper;
    private FindProfilePictureFacebookTask findProfilePictureFacebookTask;
    private UserParse userParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        if(ParseUser.getCurrentUser() != null){
            navegar();
        }
        app = (App) getApplication();
        attacher = PullToRefreshAttacher.get(this);
        uiHelper = new MainUiHelper(getWindow().getDecorView().findViewById(android.R.id.content));
        uiHelper.facebook.setOnClickListener(configurarOnFacebookClickListener());
        configurarBackground();
    }

    private void verificaStatus(StatusEnum status){
        if(status == StatusEnum.INICIO){
            statusInicio();
        }else if(status == StatusEnum.EXECUTANDO){
            statusExecutando();
        }else if(status == StatusEnum.EXECUTADO){
            statusExecutado();
        }
    }

    private void statusInicio() {
        if(app.isInternetConnection()){
            ParseFacebookUtils.logIn(
                    Arrays.asList(ParseFacebookUtils.Permissions.User.ABOUT_ME, ParseFacebookUtils.Permissions.User.BIRTHDAY),
                    MainActivity.this, callBackLoginFacebook());
            attacher.setRefreshing(true);
            verificaStatus(StatusEnum.EXECUTANDO);
        }
    }

    private void statusExecutando() {
        attacher.setRefreshing(true);
    }

    private void statusExecutado() {
        navegar();
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
                verificaStatus(StatusEnum.INICIO);
            }
        };
    }

    private LogInCallback callBackLoginFacebook() {
        return new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException error) {
                if(error == null){
                    if (user != null) {
                        requestProfilePicture(ParseFacebookUtils.getSession());
                    }
                }else{
                    //TODO Exibir mensagem de erro
                }
            }

            private void requestProfilePicture(final Session session) {
                Request request = Request.newMeRequest(session,
                        new Request.GraphUserCallback() {
                            @Override
                            public void onCompleted(final GraphUser user, Response response) {
                                if (session == Session.getActiveSession()) {
                                    if (user != null) {
                                        String facebookId = user.getId();
                                        userParse = (UserParse) ParseUser.getCurrentUser();
                                        userParse.setBirthday(user.getBirthday());
                                        userParse.setFirstName(user.getFirstName());
                                        userParse.setMiddleName(user.getMiddleName());
                                        userParse.setLastName(user.getLastName());
                                        findProfilePictureFacebookTask = new FindProfilePictureFacebookTask(facebookId,
                                                configurarFindProfilePictureCallback());
                                        findProfilePictureFacebookTask.execute();
                                    }
                                }
                                if (response.getError() != null) {
                                    // Handle error
                                }
                            }

                            private Callback configurarFindProfilePictureCallback() {
                                return new Callback() {
                                    @Override
                                    public void onReturn(Exception error, Object... objects) {
                                        if(error == null){
                                            Bitmap bitmap = (Bitmap) objects[0];
                                            String imageFileName = userParse.getObjectId() + ".jpg";
                                            ParseFile picture = new ParseFile(imageFileName, TripUtil.convertBitmapToBytes(bitmap));
                                            userParse.setProfilePicture(picture);
                                            UserParseService.saveUser(userParse, configurarSaveUserCallback());
                                        }
                                    }

                                    private SaveCallback configurarSaveUserCallback() {
                                        return new SaveCallback() {
                                            @Override
                                            public void done(ParseException error) {
                                                UserParseService.saveUserInLocal(userParse, configurarSaveUserInLocalCallback());
                                            }

                                            private SaveCallback configurarSaveUserInLocalCallback() {
                                                return new SaveCallback() {
                                                    @Override
                                                    public void done(ParseException e) {
                                                        verificaStatus(StatusEnum.EXECUTADO);
                                                    }
                                                };
                                            }
                                        };
                                    }
                                };
                            }
                        });
                request.executeAsync();
            };
        };
    }

    private void navegar() {
        NavegacaoUtil.navegar(MainActivity.this, DrawerLayoutMain.class);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(findProfilePictureFacebookTask != null){
            findProfilePictureFacebookTask.cancel(true);
        }
    }
}
