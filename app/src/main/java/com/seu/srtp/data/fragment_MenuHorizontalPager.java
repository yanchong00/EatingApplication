package com.seu.srtp.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.seu.srtp.main.R;

import java.util.List;

/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class fragment_MenuHorizontalPager extends Fragment {
    private List<Menu> menuList;
    private static String ARGS="storeNumber";


    //构造函数，传入一个storeNumber
    public static fragment_MenuHorizontalPager newInstance(int storeNumber){
        Bundle bundle=new Bundle();
        fragment_MenuHorizontalPager fragmentMenuHorizontalPager =new fragment_MenuHorizontalPager();
        bundle.putInt(ARGS,storeNumber);
        fragmentMenuHorizontalPager.setArguments(bundle);
        return fragmentMenuHorizontalPager;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,  final ViewGroup container,  final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_horizontal, container, false);
    }

    @Override
    public void onViewCreated(final View view,  final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new MenuHorizontalPagerAdapter(getActivity(),getFragmentManager(), getArguments().getInt(ARGS)));

        horizontalInfiniteCycleViewPager.setScrollDuration(400);
        horizontalInfiniteCycleViewPager.setInterpolator(
                AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator)
        );
        horizontalInfiniteCycleViewPager.setMediumScaled(false);
        horizontalInfiniteCycleViewPager.setMaxPageScale(0.9F);//整个卡片的大小
        horizontalInfiniteCycleViewPager.setMinPageScale(0.6F);
        horizontalInfiniteCycleViewPager.setCenterPageScaleOffset(30.0F);//30
        horizontalInfiniteCycleViewPager.setMinPageScaleOffset(5.0F);//5.0

        horizontalInfiniteCycleViewPager.setCurrentItem(
                horizontalInfiniteCycleViewPager.getRealItem() + 1
        );
    }
}
