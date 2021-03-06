package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("Guide")
public class GuideParse extends ParseObject {
    public static final String USER = "user";
    public static final String TITLE = "title";
    public static final String SIGNED = "signed";
    public static final String TAG = "tag";
    public static final String PLACES = "places";
    public UserParse getUser(){
        return (UserParse) getParseUser(USER);
    }
    public String getTitle(){
        return getString(TITLE);
    }
    public int getSigned(){
        return getInt(SIGNED);
    }
    public ParseRelation<Tag> getTags(){
        return getRelation(TAG);
    }
    public ParseRelation<PlaceParse> getPlaces(){
        return getRelation(PLACES);
    }
}
