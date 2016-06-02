package com.github.brnunes.swipeablecardview;

/**
 * Created by asus on 24-Apr-16.
 */
public class Config {
    //URL to our login.php file
    public static final String LOGIN_URL = "http://128.199.93.60/ships/index.php/Sign/in";
    public static final String DESC_URL = "http://128.199.93.60/ships/index.php/desc/";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_ID = "IDVessel";
    public static final String KEY_NAME = "Name";
    public static final String KEY_IMO = "IMONumber";
    public static final String KEY_GROSS = "GrossTonnage";
    public static final String KEY_REG = "RegisteredOwner";
    public static final String KEY_CLASS = "ClassificationSociety";
    public static final String KEY_FLAG = "Flag";
    public static final String KEY_SHIP = "ShippingRoute";
    public static final String KEY_L = "Length";
    public static final String KEY_B = "Breadth";
    public static final String KEY_H = "High";
    public static final String KEY_NAMES = "NameOfSurveyor";

    public static final String KEY_ADDRESS = "address";
    public static final String KEY_VC = "vc";
    public static final String JSON_ARRAY = "result";
    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
