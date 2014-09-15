package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by vagnnermartins on 11/09/14.
 */
@ParseClassName("Tips")
public class TipsParse extends ParseObject {
   public static final String GUIDE = "guide";
   public static final String DESCRIPTION = "description";

    public GuideParse getGuide(){
        return (GuideParse) getParseObject(GUIDE);
    }
    public String getDescription(){
        return getString(DESCRIPTION);
    }
    public void setGuide(GuideParse guide){
        put(GUIDE, guide);
    }
    public void setDescription(String description){
        put(DESCRIPTION, description);
    }
}
