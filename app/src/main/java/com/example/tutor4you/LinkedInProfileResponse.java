//package com.example.tutor4you;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ParseException;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.Calendar;
//
//import javax.net.ssl.HttpsURLConnection;
//
//import static com.example.tutor4you.Constants.ACCESS_TOKEN_URL;
//import static com.example.tutor4you.Constants.AMPERSAND;
//import static com.example.tutor4you.Constants.API_KEY;
//import static com.example.tutor4you.Constants.AUTHORIZATION_URL;
//import static com.example.tutor4you.Constants.CLIENT_ID_PARAM;
//import static com.example.tutor4you.Constants.EQUALS;
//import static com.example.tutor4you.Constants.GRANT_TYPE;
//import static com.example.tutor4you.Constants.GRANT_TYPE_PARAM;
//import static com.example.tutor4you.Constants.QUESTION_MARK;
//import static com.example.tutor4you.Constants.REDIRECT_URI;
//import static com.example.tutor4you.Constants.REDIRECT_URI_PARAM;
//import static com.example.tutor4you.Constants.RESPONSE_TYPE_PARAM;
//import static com.example.tutor4you.Constants.RESPONSE_TYPE_VALUE;
//import static com.example.tutor4you.Constants.SECRET_KEY;
//import static com.example.tutor4you.Constants.SECRET_KEY_PARAM;
//import static com.example.tutor4you.Constants.STATE;
//import static com.example.tutor4you.Constants.STATE_PARAM;
//
//public class LinkedInProfileResponse extends AppCompatActivity {
//
//    /*CONSTANT FOR THE AUTHORIZATION PROCESS*/
//
//    String profileUrl = "https://api.linkedin.com/v2/me?projection=(id,firstName,lastName,profilePicture(displayImage~:playableStreams))";
//    String accessToken;
//    String linkedInUserEmailAddress;
//    SharedPreferences sharedPreferences;
//    String emailAddress = "https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))";
//    private WebView webView;
//    //TransparentDialog mProgressBarHandler;
//    private ProgressDialog pd;
//    String deviceId, location, country;
//    String linkedInUserId, linkedInUserFirstName, linkedInUserLastName, linkedInUserProfile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_linkedin);
//
//        //get the webView from the layout
//        webView = (WebView) findViewById(R.id.main_activity_web_view);
//        deviceId = getIntent().getStringExtra("deviceId");
//        location = getIntent().getStringExtra("location");
//        country = getIntent().getStringExtra("country");
//        //Request focus for the webview
//        webView.requestFocus(View.FOCUS_DOWN);
//        webView.clearHistory();
//        webView.clearCache(true);
//        sharedPreferences = MyApplic22ation.preference;
//        pd = ProgressDialog.show(this, "", "Loading...", true);
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                //This method will be executed each time a page finished loading.
//                //The only we do is dismiss the progressDialog, in case we are showing any.
//                if (pd != null && pd.isShowing()) {
//                    pd.dismiss();
//                }
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String authorizationUrl) {
//                //This method will be called when the Auth proccess redirect to our RedirectUri.
//                //We will check the url looking for our RedirectUri.
//                if (authorizationUrl.startsWith(REDIRECT_URI)) {
//                    Log.i("Authorize", "");
//                    Uri uri = Uri.parse(authorizationUrl);
//                    //We take from the url the authorizationToken and the state token. We have to check that the state token returned by the Service is the same we sent.
//                    //If not, that means the request may be a result of CSRF and must be rejected.
//                    String stateToken = uri.getQueryParameter(STATE_PARAM);
//                    if (stateToken == null || !stateToken.equals(STATE)) {
//                        Log.e("Authorize", "State token doesn't match");
//                        return true;
//                    }
//
//                    //If the user doesn't allow authorization to our application, the authorizationToken Will be null.
//                    String authorizationToken = uri.getQueryParameter(RESPONSE_TYPE_VALUE);
//                    if (authorizationToken == null) {
//                        Log.i("Authorize", "The user doesn't allow authorization.");
//                        return true;
//                    }
//                    Log.i("Authorize", "Auth token received: " + authorizationToken);
//
//                    //Generate URL for requesting Access Token
//                    String accessTokenUrl = getAccessTokenUrl(authorizationToken);
//                    //We make the request in a AsyncTask
//                    new PostRequestAsyncTask().execute(accessTokenUrl);
//
//                } else {
//                    //Default behaviour
//                    Log.i("Authorize", "Redirecting to: " + authorizationUrl);
//                    webView.loadUrl(authorizationUrl);
//                }
//                return true;
//            }
//        });
//        String authUrl = getAuthorizationUrl();
//        Log.i("Authorize", "Loading Auth Url: " + authUrl);
//        webView.loadUrl(authUrl);
//    }
//
//    /**
//     * Method that generates the url for get the access token from the Service
//     *
//     * @return Url
//     */
//    private static String getAccessTokenUrl(String authorizationToken) {
//        return ACCESS_TOKEN_URL
//                + QUESTION_MARK
//                + GRANT_TYPE_PARAM + EQUALS + GRANT_TYPE
//                + AMPERSAND
//                + RESPONSE_TYPE_VALUE + EQUALS + authorizationToken
//                + AMPERSAND
//                + CLIENT_ID_PARAM + EQUALS + API_KEY
//                + AMPERSAND
//                + REDIRECT_URI_PARAM + EQUALS + REDIRECT_URI
//                + AMPERSAND
//                + SECRET_KEY_PARAM + EQUALS + SECRET_KEY;
//    }
//
//    /**
//     * Method that generates the url for get the authorization token from the Service
//     *
//     * @return Url
//     */
//    private static String getAuthorizationUrl() {
//        return AUTHORIZATION_URL
//                + QUESTION_MARK + RESPONSE_TYPE_PARAM + EQUALS + RESPONSE_TYPE_VALUE
//                + AMPERSAND + CLIENT_ID_PARAM + EQUALS + API_KEY
//                + AMPERSAND + STATE_PARAM + EQUALS + STATE
//                + AMPERSAND + REDIRECT_URI_PARAM + EQUALS + REDIRECT_URI + "&scope=r_liteprofile%20r_emailaddress%20w_member_social";
//    }
//
//
//    private class PostRequestAsyncTask extends AsyncTask<String, Void, Boolean> {
//
//        @Override
//        protected void onPreExecute() {
//            pd = ProgressDialog.show(LinkedInProfileResponse.this, "", "Loading", true);
//        }
//
//        @Override
//        protected Boolean doInBackground(String... urls) {
//            if (urls.length > 0) {
//                String url = urls[0];
//                OkHttpClient httpClient = new OkHttpClient();
//                HttpPost httpost = new HttpPost(url);
//                try {
//                    Response response = httpClient.execute(httpost);
//                    if (response != null) {
//                        //If status is OK 200
//                        if (response.getStatusLine().getStatusCode() == 200) {
//                            String result = EntityUtils.toString(response.getEntity());
//                            JSONObject resultJson = new JSONObject(result);
//                            int expiresIn = resultJson.has("expires_in") ? resultJson.getInt("expires_in") : 0;
//                            String accessToken = resultJson.has("access_token") ? resultJson.getString("access_token") : null;
//                            Log.e("Tokenm", "" + accessToken);
//                            if (expiresIn > 0 && accessToken != null) {
//                                Log.i("Authorize", "This is the access Token: " + accessToken + ". It will expires in " + expiresIn + " secs");
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.add(Calendar.SECOND, expiresIn);
//                                long expireDate = calendar.getTimeInMillis();
//                                SharedPreferences preferences = LinkedInProfileResponse.this.getSharedPreferences("user_info", 0);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.putLong("expires", expireDate);
//                                editor.putString("accessToken", accessToken);
//                                editor.commit();
//
//                                return true;
//                            }
//                        }
//                    }
//                } catch (IOException e) {
//                    Log.e("Authorize", "Error Http response " + e.getLocalizedMessage());
//                } catch (ParseException e) {
//                    Log.e("Authorize", "Error Parsing Http response " + e.getLocalizedMessage());
//                } catch (JSONException e) {
//                    Log.e("Authorize", "Error Parsing Http response " + e.getLocalizedMessage());
//                }
//            }
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean status) {
//            if (pd != null && pd.isShowing()) {
//                pd.dismiss();
//            }
//            if (status) {
//                SharedPreferences preferences = LinkedInProfileResponse.this.getSharedPreferences("user_info", 0);
//                accessToken = preferences.getString("accessToken", null);
//                try {
//                    if (accessToken != null) {
//                        new GetProfileRequestAsyncTask().execute(profileUrl);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    public void sendGetRequest(String urlString, String accessToken) throws Exception {
//        URL url = new URL(urlString);
//        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Authorization", "Bearer " + accessToken);
//        con.setRequestProperty("cache-control", "no-cache");
//        con.setRequestProperty("X-Restli-Protocol-Version", "2.0.0");
//        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        StringBuilder jsonString = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            jsonString.append(line);
//        }
//        JSONObject jsonObject = new JSONObject(jsonString.toString());
//        Log.d("Complete json object", jsonObject.toString());
//        try {
//            linkedInUserId = jsonObject.getString("id");
//            String country = jsonObject.getJSONObject("firstName").getJSONObject("preferredLocale").getString("country");
//            String language = jsonObject.getJSONObject("firstName").getJSONObject("preferredLocale").getString("language");
//            String getFirstnameKey = language + "_" + country;
//            linkedInUserFirstName = jsonObject.getJSONObject("firstName").getJSONObject("localized").getString(getFirstnameKey);
//            linkedInUserLastName = jsonObject.getJSONObject("firstName").getJSONObject("localized").getString(getFirstnameKey);
//            linkedInUserProfile = jsonObject.getJSONObject("profilePicture").getJSONObject("displayImage~").getJSONArray("elements").getJSONObject(0).getJSONArray("identifiers").getJSONObject(0).getString("identifier");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void sendGetRequestForEmail(String urlString, String accessToken) throws Exception {
//        URL url = new URL(urlString);
//        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Authorization", "Bearer " + accessToken);
//        con.setRequestProperty("cache-control", "no-cache");
//        con.setRequestProperty("X-Restli-Protocol-Version", "2.0.0");
//        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        StringBuilder jsonString = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            jsonString.append(line);
//        }
//        JSONObject jsonObject = new JSONObject(jsonString.toString());
//        linkedInUserEmailAddress = jsonObject.getJSONArray("elements").getJSONObject(0).getJSONObject("handle~").getString("emailAddress");
//        Log.d("email json object", jsonObject.toString());
//        sendRequestToServerForLinkwedInIntegration();
//
//
//    }
//
//    public void sendRequestToServerForLinkwedInIntegration() {
//
//        if (AppUtils.isInternetOn(LinkedInProfileResponse.this)) {
//
//            JSONObject userJsonObject = new JSONObject();
//            try {
//                userJsonObject.put(NetworkKeys.EMAIL, linkedInUserEmailAddress);
//                userJsonObject.put(NetworkKeys.USERNAME, linkedInUserFirstName + " " + linkedInUserLastName);
//                userJsonObject.put(NetworkKeys.CONTACTNO, "");
//                userJsonObject.put(NetworkKeys.UID, linkedInUserId);
//                userJsonObject.put(NetworkKeys.PROVIDER, "LinkedIn");
//                userJsonObject.put(NetworkKeys.IMAGE, linkedInUserProfile);
//                userJsonObject.put(NetworkKeys.DEVICE_TOKEN, deviceId);
//                userJsonObject.put(NetworkKeys.LOCATION, location);
//                userJsonObject.put(NetworkKeys.COUNTRY, country);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String url = Constants.WebServices.SOCIAL_MEDIA_LOGIN;
//            CallWebService.getInstance(NewLinkedInIntegration.this, true).hitJSONObjectVolleyWebServicemanageclubdetailsWithoutAccessToken(Request.Method.POST, url, deviceId, userJsonObject, new CallBackInterfaceVolley() {
//                @Override
//                public void onJsonObjectSuccess(JSONObject object) {
//                    pd.dismiss();
//                    try {
//                        boolean success = object.getBoolean(NetworkKeys.SUCCESS);
//
//                        if (success) {
//                            JSONObject userInfoJsonObject = object.getJSONObject(NetworkKeys.USERJSONOBJECT);
//                            String userId = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USERID);
//                            String userEmail = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_EMAIL);
//                            String userImage = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_IMAGE);
//                            String userName = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_NAME);
//                            String userCity = userInfoJsonObject.getString(NetworkKeys.USER_CITY);
//                            String contactNo = userInfoJsonObject.getString(NetworkKeys.CONTACT_NO);
//                            String userCountry = userInfoJsonObject.getString(NetworkKeys.USER_COUNTRY);
//                            String isNotificationOn = userInfoJsonObject.getString(NetworkKeys.ISNOTIFICATION);
//                            String userLocation = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_LOCATION);
//                            String signInUserType = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_PROVIDER);
//                            String userAuthToken = userInfoJsonObject.getString(NetworkKeys.SIGN_IN_USER_AUTHTOKEN);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString(NetworkKeys.SIGN_IN_USERID, userId);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_EMAIL, userEmail);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_IMAGE, userImage);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_NAME, userName);
//                            editor.putString(NetworkKeys.USER_CITY, userCity);
//                            editor.putString(NetworkKeys.USER_COUNTRY, userCountry);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_MOBILE, contactNo);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_LOCATION, userLocation);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_PROVIDER, signInUserType);
//                            editor.putString(NetworkKeys.ISNOTIFICATION, isNotificationOn);
//                            editor.putString(NetworkKeys.SIGN_IN_USER_AUTHTOKEN, userAuthToken);
//                            editor.putBoolean(NetworkKeys.IS_USER_LOGIN_FROM_SOCIAL_MEDIA, true);
//                            editor.putBoolean(NetworkKeys.SIGN_IN_USER_SUCCESSFULLY, true);
//                            editor.apply();
//                            Intent intent = new Intent(LinkedInProfileResponse.this, CardSelctionActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            pd.dismiss();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
////                @Override
////                public void onJsonArrarSuccess(JSONArray array) {
////
////                }
////
////                @Override
////                public void onFailure(String str) {
////                    pd.dismiss();
////                }
////            });
//        } else {
//            AppUtils.showToast(LinkedInProfileResponse.this, getResources().getString(R.string.internet_connection));
//        }
//    }
//
//
//    private class GetProfileRequestAsyncTask extends AsyncTask<String, Void, JSONObject> {
//
//        @Override
//        protected void onPreExecute() {
//            pd = ProgressDialog.show(LinkedInProfileResponse.this, "", "Loading..", true);
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... urls) {
//            if (urls.length > 0) {
//                try {
//                    sendGetRequest(profileUrl, accessToken);
//                    sendGetRequestForEmail(emailAddress, accessToken);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject data) {
//            if (pd != null && pd.isShowing()) {
//                pd.dismiss();
//            }
//            if (data != null) {
//
//                try {
//                    String welcomeTextString = String.format("Welcome %1$s %2$s, You are a %3$s", data.getString("firstName"), data.getString("lastName"), data.getString("headline"));
//
//                } catch (JSONException e) {
//                    Log.e("Authorize", "Error Parsing json " + e.getLocalizedMessage());
//                }
//            }
//        }
//
//
//    }
//
//}
