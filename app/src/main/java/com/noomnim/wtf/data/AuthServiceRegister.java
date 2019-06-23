package com.noomnim.wtf.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.noomnim.wtf.activities.LoginActivity;
import com.noomnim.wtf.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class AuthServiceRegister {

    private static AuthServiceRegister instance = new AuthServiceRegister();

    public static AuthServiceRegister getInstance(){
        return instance;
    }

    public AuthServiceRegister() {
    }

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void registerUser(String email, String password, Context context, final LoginActivity.RegisterInterface listener){
        try{

            String url = Constants.REGISTER;

            JSONObject jsonBody = new JSONObject(  );
            jsonBody.put( "email",email );
            jsonBody.put( "password", password );

            final String mRequestBody = jsonBody.toString();

            StringRequest registerRequest = new StringRequest( Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Volley", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(error.networkResponse.statusCode == 409){
                        listener.success(true);
                    }
                }
            } ) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    return mRequestBody.getBytes( StandardCharsets.UTF_8 );
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    if(response.statusCode == 200 || response.statusCode == 409){
                        listener.success(true);
                    }
                    return super.parseNetworkResponse( response );
                }
            };

            Volley.newRequestQueue( context ).add( registerRequest );

        }catch (JSONException e){
            e.getLocalizedMessage();
        }
    }
}
