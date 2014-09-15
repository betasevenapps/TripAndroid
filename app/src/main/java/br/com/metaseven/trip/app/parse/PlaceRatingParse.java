package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("PlaceRating")
public class PlaceRatingParse extends ParseObject {
    public static final String USER = "user";
    public static final String PLACE = "place";
    public static final String RATING = "rating";
    public static final String COMMENT = "comment";

    public ParseUser getUser(){
        return getParseUser(USER);
    }
    public PlaceParse getPlace(){
        return (PlaceParse) getParseObject(PLACE);
    }
    public int getRating(){
        return getInt(RATING);
    }
    public String getComment(){
        return getString(COMMENT);
    }
    public void setUser(ParseUser user){
        put(USER, user);
    }
    public void setPlace(GuideParse place){
        put(PLACE, place);
    }
    public void setComment(String comment){
        put(COMMENT, comment);
    }
}
