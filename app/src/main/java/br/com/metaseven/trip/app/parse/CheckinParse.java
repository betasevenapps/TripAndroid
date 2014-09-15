package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("Checkin")
public class CheckinParse extends ParseObject {
    public static final String USER = "user";
    public static final String COMMENT = "comment";
    public static final String PLACE = "place";
    public static final String PICTURE = "picture";
    public ParseUser getUser(){
        return getParseUser(USER);
    }
    public String getComment(){
        return getString(COMMENT);
    }
    public PlaceParse getPlace(){
        return (PlaceParse) getParseObject(PLACE);
    }
    public ParseFile getPicture(){
        return getParseFile(PICTURE);
    }
    public void setUser(ParseUser user){
        put(USER, user);
    }
    public void setComment(String comment){
        put(COMMENT, comment);
    }
    public void setPlace(PlaceParse place){
        put(PLACE, place);
    }
    public void setPicture(ParseFile picture){
        put(PICTURE, picture);
    }
}
