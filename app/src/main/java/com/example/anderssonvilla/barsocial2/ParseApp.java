package com.example.anderssonvilla.barsocial2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;



/**
 * Created by andersson.villa on 5/06/15.
 */
public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //(this,aplicationID,clientkey)
       Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ktcd0GdngHSX6N2I4czCPD7ZJ7RmMwk6FKJfSVmh", "T0z8hJBtGFi5WJAlUt4cLQY3l6JxwPGSSNVAxukc");

        ParseUser.enableAutomaticUser();
        ParseACL defauAcl=new ParseACL();
        defauAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defauAcl,true);
    }

}
