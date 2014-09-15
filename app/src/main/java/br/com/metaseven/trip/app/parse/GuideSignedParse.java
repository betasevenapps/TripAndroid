package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("GuideSigned")
public class GuideSignedParse extends ParseObject {
    public static final String USER = "user";
    public static final String GUIDE = "guide";

    public ParseUser getUser(){
        return getParseUser(USER);
    }
    public GuideParse getGuide(){
        return (GuideParse) getParseObject(GUIDE);
    }
    public void setUser(ParseUser user){
        put(USER, user);
    }
    public void setGuide(GuideParse guide){
        put(GUIDE, guide);
    }
}
