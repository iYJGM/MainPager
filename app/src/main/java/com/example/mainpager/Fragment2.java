package com.example.mainpager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    private ListView listView;
    private static String jsonURL = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=1&size=20&offset=0";//json数据网址

    private List<Music> newsBeanList;
    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment2, container, false);
        listView = (ListView)view.findViewById(R.id.lv_musiclist);
        new NewsAsyncTask().execute(jsonURL);
        return view;
    }



    private List<Music> getJsonData(String url) {
        List<Music> newsBeanList = new ArrayList<Music>();//保存解析出来的所有的数据
        try {
            //获取到json字符串
            String jsonString = readStream(new URL(url).openStream());//和url.openConnection().getInputStream()一样
            Music newsBean;
            JSONObject jsonObject;
            String newsUrl = null;
            String newsTitle = null;
            String newsContent = null;
            String duration = null;
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("song_list");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                newsUrl = jsonObject.getString("pic_small");//图片网址
                System.out.println(newsUrl);
                newsTitle = jsonObject.getString("title");//title
                System.out.println(newsTitle);
                newsContent = jsonObject.getString("author");//歌手
                System.out.println(newsContent);
                int musicTime = jsonObject.getInt("file_duration");//时间
                duration = musicTime / 60 + ":" + musicTime % 60;
                System.out.println(duration);
                newsBean = new Music(newsUrl, newsTitle, newsContent, duration);
                newsBeanList.add(newsBean);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newsBeanList;
    }

    /**
     * 解析网络返回的数据
     */
    private String readStream(InputStream is) {
        InputStreamReader isReader;
        String result = "";
        String line = "";
        try {
            isReader = new InputStreamReader(is, "utf-8");//将字节流转化为字符流
            BufferedReader buffReader = new BufferedReader(isReader);//从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
            while ((line = buffReader.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 构造一个AsyncTask，传入String类型的URL，返回一个NewsBean对象，每一个对象就是
     * listview中的每一行数据，包括一个icon,title,content
     */
    class NewsAsyncTask extends AsyncTask<String, Void, List<Music>> {

        @Override
        protected List<Music> doInBackground(String... params) {
            return getJsonData(params[0]);

            // return parseXMLWithPull(params[0]);
        }

        @Override
        protected void onPostExecute(List<Music> result) {
            super.onPostExecute(result);
            // 访问网络并解析json成功后返回结果，即我们设置的List<Music>
            // sendByOKHttp();
            MusicAdapter adapter = new MusicAdapter(getActivity(),result,listView);
            listView.setAdapter(adapter);
        }

    }


}
