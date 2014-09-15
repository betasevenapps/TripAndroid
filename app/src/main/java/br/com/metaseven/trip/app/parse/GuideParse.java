package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("Guide")
public class GuideParse extends ParseObject {
    public static final String TITLE = "title";
    public static final String SIGNED = "signed";
    public static final String TAG = "tag";

    public String getTitle(){
        return getString(TITLE);
    }
    public int getSigned(){
        return getInt(SIGNED);
    }
    public ParseRelation<Tag> tags(){
        return getRelation(TAG);
    }
}
