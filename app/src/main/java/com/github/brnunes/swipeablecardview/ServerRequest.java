package com.github.brnunes.swipeablecardview;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by asus on 24-Apr-16.
 */
public class ServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000*30;
    public static final String SERVER_ADDRESS =  "http://128.199.93.60/";
    public ServerRequest(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait");
    }
    public void storeUserDataInBackground(User user, GetUserCallback userCallback)
    {

        progressDialog.show();
        new StoreUserDataAsyncTask(user,userCallback).execute();
    }
    public void fetchUserDataInBackground(User user, GetUserCallback userCallback)
    {
        progressDialog.show();
        new FetchUserDataAsyncTask(user,userCallback).execute();
    }
    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNameValuePair("age", user.status));
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                try {
                    client.execute(post);
                } catch (IOException e) {  //E
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.Done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }
        User returnedUser=null;

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("username", user.email));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData2.php");


            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                try {
                    HttpResponse httpResponse = client.execute(post);

                    HttpEntity entity = httpResponse.getEntity();
                    String result = EntityUtils.toString(entity);
                    JSONObject jObject = new JSONObject(result);

                    if (jObject.length() == 0) {
                        returnedUser = null;
                    } else {
                        String name = jObject.getString("name");
                        String status = jObject.getString("status");

                        returnedUser = new User(name, status, user.username, user.password);
                    }

                } catch (IOException e) {  //E
                    e.printStackTrace();
                } catch (JSONException e) { //E
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return returnedUser;
        }
        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.Done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
}
