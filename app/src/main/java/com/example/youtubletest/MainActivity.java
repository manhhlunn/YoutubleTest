package com.example.youtubletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends  YouTubeBaseActivity  {
    public static final String KEY="AIzaSyA9nguEzyzOcyutAofXLnSZ6HcrKcX78AI";
    YouTubePlayerView youTubePlayerView;
    public static final String PLAYLIST="PL22yUKv0i0HxGMIQikvIGbnZe6a9k03Qj";
    public static final String URL="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+PLAYLIST+"&key="+KEY+"&maxResults=50";
    ListView listView;
    int count=0;
    YouTubePlayer myPlay;
    CustomAdapter customAdapter;
    /// ah đc r
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView=findViewById(R.id.myVid);
        listView=findViewById(R.id.listView);
        setVideos();
        getPlaylist(URL, new Callback() {
            @Override
            public void callback(List<Video> list) {
                customAdapter=new CustomAdapter(MainActivity.this,R.layout.line_list,list);
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(myPlay!=null)
                        {
                            myPlay.loadVideo(list.get(i).getId());
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            Toast.makeText(this, "Lỗi Rồi sao t ko chuyển đc qua android", Toast.LENGTH_SHORT).show();
        }
    }
    public void getPlaylist(String url,Callback callback)
    {
        JsonObjectRequest jo=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("items");
                    JSONObject jsonItem;
                    JSONObject jsonItemData;
                    List <Video> list=new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        count++;
                        jsonItem= jsonArray.getJSONObject(i);
                        jsonItemData=jsonItem.getJSONObject("snippet");
                        String author=jsonItemData.getString("videoOwnerChannelTitle");
                        String name=jsonItemData.getString("title");
                        String description=jsonItemData.getString("description");
                        JSONObject thumbnails=jsonItemData.getJSONObject("thumbnails");
                        JSONObject thumbnailsMax=thumbnails.getJSONObject("high");
                        JSONObject resid=jsonItemData.getJSONObject("resourceId");
                        String id=resid.getString("videoId");
                        String url=thumbnailsMax.getString("url");
                        Video video=new Video(url,name,author,description,id);
                        list.add(video);
                    }
                    callback.callback(list);


                } catch (JSONException e) {
                    Log.d("AAA",e.toString()+count);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jo);;
    }
    public void setVideos()
    {
            youTubePlayerView.initialize(KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b)
                {
                    myPlay=youTubePlayer;
                }
                
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if(youTubeInitializationResult.isUserRecoverableError())
                {
                    youTubeInitializationResult.getErrorDialog(MainActivity.this,1).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}