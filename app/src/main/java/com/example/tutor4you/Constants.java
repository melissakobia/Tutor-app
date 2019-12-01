package com.example.tutor4you;

public class Constants {

    public static final String YELP_BASE_URL = "https://api.linkedin.com/v2/";
    public static final String YELP_API_KEY = "Bearer ";

    public static final String  FIREBASE_CHILD_PROFILE = "profiles";

    /****FILL THIS WITH YOUR INFORMATION*********/
//This is the public api key of our application
    public static final String API_KEY = "86vhpkaw8vjbwc";
    //This is the private api key of our application
    public static final String SECRET_KEY = "7x6cMOQe5RedTK2S";
    //This is any string we want to use. This will be used for avoiding CSRF attacks. You can generate one here: http://strongpasswordgenerator.com/
    public static final String STATE = "DCEeFWf45A53sdfKef424";
    //This is the url that LinkedIn Auth process will redirect to. We can put whatever we want that starts with http:// or https:// .
//We use a made up url that we will intercept when redirecting. Avoid Uppercases.
    public static final String REDIRECT_URI = "https://tutor4you.example.com/auth/callback";
    /*********************************************/


}
