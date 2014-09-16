package br.com.metaseven.trip.app.service;

import com.parse.SaveCallback;

import br.com.metaseven.trip.app.parse.UserParse;

/**
 * Created by vagnnermartins on 16/09/14.
 */
public class UserParseService {

    private static final String PIN_USER = "pin_user";

    public static void saveUser(UserParse user, SaveCallback callback){
        user.saveInBackground(callback);
    }

    public static void saveUserInLocal(UserParse user, SaveCallback callback){
        user.pinInBackground(PIN_USER, callback);
    }
}
