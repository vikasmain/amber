package com.example.dell.myamb.hub;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dell.myamb.container.KeyContainer;
import com.example.dell.myamb.handler.ExceptionHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by vikas bajpayee on 10-08-2016.
 */
public class Connect {
    private Context context = null;
    private Connect connect = null;
    private String plaintext = null;
    private ArrayList<PairHelper> returnResult = null;
    private KeyContainer keyContainer = new KeyContainer();
    public Connect(Context context){
        if(context == null){
            ExceptionHandler.writeError("Context cannot be null");
        }else{
            this.context = context;
        }
    }
    public void setInstance(Connect connect){
        this.connect = connect;
    }
    public Connect load(String plaintext){
        connect.plaintext = plaintext;
        return connect;
    }

    public void call(final OnTaskCompletion onTaskCompletion){
        returnResult = new ArrayList<PairHelper>();
        final PairHelper pair = new PairHelper();
        if(connect.plaintext.length()!=0) {
            new AsyncTask<String, Void, ArrayList<PairHelper>>() {

                @Override
                protected ArrayList<PairHelper> doInBackground(String... strings) {
                    DefaultHttpClient httpclient = new DefaultHttpClient();

                    try {
                        URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/entitylinking/v1.0/link");


                        URI uri = builder.build();
                        HttpPost request = new HttpPost(uri);
                        request.setHeader("Content-Type", "text/plain");
                        request.setHeader("Ocp-Apim-Subscription-Key", keyContainer.getkey1());


                        // Request body
                        StringEntity reqEntity = new StringEntity(connect.plaintext);
                        request.setEntity(reqEntity);

                        HttpResponse response = httpclient.execute(request);
                        HttpEntity entity = response.getEntity();

                        if (entity != null) {
                            JSONObject jObject = new JSONObject(EntityUtils.toString(entity)); // json
                            JSONArray data = jObject.getJSONArray("entities");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                                String getName = jsonObject.getString("name");
                                String getWikiName = jsonObject.getString("wikipediaId");
                                pair.setName(getName);
                                pair.setWikiName(getWikiName);
                                returnResult.add(pair);

                            }
                            System.out.println(EntityUtils.toString(entity));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return returnResult;
                }

                @Override
                protected void onPostExecute(ArrayList<PairHelper> strings) {
                    super.onPostExecute(strings);

                    onTaskCompletion.onComplete(strings);
                }
            }.execute(connect.plaintext);

        }else{
            ExceptionHandler.writeError("Input string is empty");
        }
    }
}
