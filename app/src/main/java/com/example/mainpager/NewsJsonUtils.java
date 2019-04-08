package com.example.mainpager;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class NewsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 把json数据转成列表对象List<Music>: Music(图片url,标题,内容等)
     * @param jsonString json数据
     * @return List<Music>
     */
    public static List<Music> readJsonNewsBeans(String jsonString) {
        List<Music> beans = new ArrayList<Music>();
        try{
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(jsonString).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get("T1348647909107");//{"T1348647909107":[{},{}..]}
            if(jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                //有skipType并且skipType=special
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                //有TAGS并且没有TAG
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }
                //没有imgextra
                if (!jo.has("imgextra")) {
                    Music news = gson.fromJson(jo, Music.class);
                    Log.i(TAG, ""+news);
                    beans.add(news);//将符合条件的news加入到 List<Music>,其中news里面有各种属性
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "readJsonNewsBeans error" );
        }
        Log.i(TAG, ""+beans);
        return beans;
    }



}