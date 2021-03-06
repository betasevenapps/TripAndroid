package br.com.metaseven.trip.app.app;

import android.app.Application;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

import br.com.metaseven.trip.app.R;
import br.com.metaseven.trip.app.constants.Keys;
import br.com.metaseven.trip.app.parse.CheckinParse;
import br.com.metaseven.trip.app.parse.GuideParse;
import br.com.metaseven.trip.app.parse.GuideRatingParse;
import br.com.metaseven.trip.app.parse.GuideSignedParse;
import br.com.metaseven.trip.app.parse.PlaceParse;
import br.com.metaseven.trip.app.parse.PlaceRatingParse;
import br.com.metaseven.trip.app.parse.Tag;
import br.com.metaseven.trip.app.parse.TipsParse;
import br.com.metaseven.trip.app.parse.UserParse;
import br.com.metaseven.trip.app.util.ConnectionDetectorUtils;

/**
 * Created by vagnnermartins on 11/09/14.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initParse();
    }

    private void initParse() {
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(CheckinParse.class);
        ParseObject.registerSubclass(GuideParse.class);
        ParseObject.registerSubclass(GuideRatingParse.class);
        ParseObject.registerSubclass(GuideSignedParse.class);
        ParseObject.registerSubclass(PlaceParse.class);
        ParseObject.registerSubclass(PlaceRatingParse.class);
        ParseObject.registerSubclass(TipsParse.class);
        ParseObject.registerSubclass(Tag.class);
        ParseObject.registerSubclass(UserParse.class);
        Parse.initialize(this, Keys.PARSE_APP_ID, Keys.PARSE_CLIENT_KEY);
        ParseFacebookUtils.initialize(Keys.FACEBOOK_APP_ID);
    }

    public boolean isInternetConnection(){
        ConnectionDetectorUtils cd = new ConnectionDetectorUtils(this);
        if (!cd.isConnectingToInternet()) {
            Toast.makeText(this, R.string.exception_erro_err_internet_disconnected, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
