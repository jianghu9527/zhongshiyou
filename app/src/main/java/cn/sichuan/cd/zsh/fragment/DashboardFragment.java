package cn.sichuan.cd.zzsy.fragment;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.sichuan.cd.zzsy.R;
import cn.sichuan.cd.zzsy.generated.callback.OnClickListener;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View viewDashboard= inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView tv= viewDashboard.findViewById(R.id.text_dashboard);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), JavaMainActivity.class);
//                startActivity(intent);
            }
        });
        return viewDashboard;
    }
}