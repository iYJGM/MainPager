package com.example.mainpager;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private ListView listview;
    private List<MusicInfo> musicInfos = null;
    private MusicListAdapter Listadapter;
    public Activity mActivity;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listview = (ListView) view.findViewById(R.id.lv_musiclist);
        musicInfos = (List<MusicInfo>) MusicUtil.getMp3Infos(mActivity);
        Listadapter = new MusicListAdapter(getActivity(), musicInfos);
        listview.setAdapter(Listadapter);
        return view;

    }


}
