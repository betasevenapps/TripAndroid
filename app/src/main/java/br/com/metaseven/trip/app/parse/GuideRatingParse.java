package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("GuideRating")
public class GuideRatingParse extends ParseObject {
    public static final String USER = "user";
    public static final String GUIDE = "guide";
    public static final String RATING = "rating";
    public static final String COMMENT = "comment";

    public ParseUser getUser(){
        return getParseUser(USER);
    }
    public GuideParse getGuide(){
        return (GuideParse) getParseObject(GUIDE);
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
    public void setGuide(GuideParse guide){
        put(GUIDE, guide);
    }
    public void setComment(String comment){
        put(COMMENT, comment);
    }
}
