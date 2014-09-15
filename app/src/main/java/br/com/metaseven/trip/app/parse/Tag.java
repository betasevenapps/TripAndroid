package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by vagnnermartins on 15/09/14.
 */
@ParseClassName("Tag")
public class Tag extends ParseObject {

    public static final String TAG = "tag";

    public String getTag(){
        return getString("tag");
    }
    public void setTag(String tag){
        put(TAG, tag);
    }
}
