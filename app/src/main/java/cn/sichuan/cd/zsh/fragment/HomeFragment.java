package cn.sichuan.cd.zzsy.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sichuan.cd.zzsy.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("-------------HomeFragment---------","----HomeFragment-----onDestroy-------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("-------------HomeFragment---------","----HomeFragment-----onResume-------------");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("-------------HomeFragment---------","---HomeFragment------onAttach-------------");
    }
}