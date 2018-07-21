package io.rakesh.pixabay.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import io.rakesh.pixabay.model.PixaBay;
import io.rakesh.pixabay.presenter.PixaBayDataFetchedCallback;

import static io.rakesh.pixabay.util.QueryBuilderUtil.getQueryURL;

public class PixaBayDataManager {

    private Gson gson;

    private static PixaBayDataManager instance;

    private RequestQueue requestQueue;

    private PixaBayDataManager(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        gson = getGson();
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        return gsonBuilder.create();
    }

    public static PixaBayDataManager getInstance(RequestQueue requestQueue) {
        if (instance == null) {
            instance = new PixaBayDataManager(requestQueue);
        }
        return instance;
    }


    public void fetchDataFromCloud(String searchKeyword, final PixaBayDataFetchedCallback callback) {
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, getQueryURL(searchKeyword),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            PixaBay pixaBay = gson.fromJson(String.valueOf(jsonObject), PixaBay.class);
                            callback.onSuccess(pixaBay);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        int errorCode = 400;
                        if(error.networkResponse != null){
                            errorCode = error.networkResponse.statusCode;
                        }
                        callback.onFailure(errorCode, "NETWORK ERROR");
                    }
                }

        );

        requestQueue.add(stringRequest);
    }
}
