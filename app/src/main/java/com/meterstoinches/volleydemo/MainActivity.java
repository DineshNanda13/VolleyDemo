package com.meterstoinches.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    public static final String url1 = "http://dummy.restapiexample.com/api/v1/employees";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

       JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url1,
               null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               try {
                   for (int i=0; i<response.length(); i++) {
                       JSONObject jsonObject = response.getJSONObject(i);
                       Log.d("ID ",jsonObject.getString("id"));
                   }
               }catch (JSONException e){
                   e.printStackTrace();
               }
              // Log.d("Response ",response.toString());
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.d("MyError ",error.getMessage());
           }
       });
       queue.add(arrayRequest);
    }
}
