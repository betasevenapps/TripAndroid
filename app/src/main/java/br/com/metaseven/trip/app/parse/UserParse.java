package br.com.metaseven.trip.app.parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by vagnnermartins on 16/09/14.
 */
@ParseClassName("_User")
public class UserParse extends ParseUser {

    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTHDAY = "birthday";
    public static final String PROFILE_PICTURE = "profile_picture";

    private ParseUser parseUser;

    public String getFirstName(){
       return getString(FIRST_NAME);
    }
    public String getMiddleName(){
       return getString(MIDDLE_NAME);
    }
    public String getLastName(){
       return getString(LAST_NAME);
    }
    public String getBirthday(){
       return getString(BIRTHDAY);
    }
    public ParseFile getProfilePicture(){
        return  getParseFile(PROFILE_PICTURE);
    }
    public void setFirstName(String firstName){
        if(firstName != null){
            put(FIRST_NAME, firstName);
        }
    }
    public void setMiddleName(String middleName){
        if(middleName != null){
            put(MIDDLE_NAME, middleName);
        }
    }
    public void setLastName(String lastName){
        if(lastName != null){
            put(LAST_NAME, lastName);
        }
    }
    public void setBirthday(String birthday){
        if(birthday != null){
            put(BIRTHDAY, birthday);
        }
    }
    public void setProfilePicture(ParseFile profilePicture){
        put(PROFILE_PICTURE, profilePicture);
    }
}
