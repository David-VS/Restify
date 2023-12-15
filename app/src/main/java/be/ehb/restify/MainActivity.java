package be.ehb.restify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import be.ehb.restify.model.ForumPost;
import be.ehb.restify.util.ForumPostAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPosts;
    private ForumPostAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ForumPostAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        rvPosts = findViewById(R.id.rv_posts);
        rvPosts.setAdapter(mAdapter);
        rvPosts.setLayoutManager(mLayoutManager);

        downloadPosts();
    }

    private void downloadPosts() {
        Thread bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mClient = new OkHttpClient();

                    Request mRequest = new Request.Builder()
                            .url("https://jsonplaceholder.typicode.com/posts")
                            .get()
                            .build();

                    Response mResponse = mClient.newCall(mRequest).execute();
                    String responsePlain = mResponse.body().string();
                    JSONArray mJsonArray = new JSONArray(responsePlain);
                    int itemCount = mJsonArray.length();
                    int i = 0;

                    while(i < itemCount){
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        final ForumPost curentPost = new ForumPost(
                            mJsonObject.getInt("userId"),
                            mJsonObject.getInt("id"),
                            mJsonObject.getString("title"),
                            mJsonObject.getString("body")
                        );
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.addItem(curentPost);
                            }
                        });
                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        bgThread.start();
    }
}