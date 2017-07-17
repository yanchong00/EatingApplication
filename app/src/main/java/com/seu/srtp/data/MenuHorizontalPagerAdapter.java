package com.seu.srtp.data;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.srtp.main.R;

import java.util.List;


/**
 * Created by GIGAMOLE on 7/27/16.
 */
public class MenuHorizontalPagerAdapter extends PagerAdapter implements View.OnClickListener{
    private Context mContext;
    private FragmentManager fm;
    private LayoutInflater mLayoutInflater;
    private List<Menu> menuList;
    private int storeNumber;
    private final Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.mipmap.xiaoone,
                    "酱炒黄豆芽"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaotwo,
                    "清炒大白菜"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaothree,
                    "肉末豆腐"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaofour,
                    "南瓜"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaofive,
                    "菜秧"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaosix,
                    "手撕包菜"
            ),
            new Utils.LibraryObject(
                    R.mipmap.xiaoseven,
                    "肉沫蒸蛋"
            )
    };



    public MenuHorizontalPagerAdapter(final Context context, final FragmentManager Fm,int storeNumber) {//,List<Menu> menuList
        mContext = context;
        fm=Fm;
       // this.menuList=menuList;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item_menuhorizon, container, false);
        Utils.setupItem(view, LIBRARIES[position]);
        view.setOnClickListener(this);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        int i=getItemPosition(v);
        if(i==0){
            fragment_MenuDescrition temp=fragment_MenuDescrition.newInstance(1);
            temp.show(fm,"temptext");
            Log.d("00","0000000000000000000000");
        }
        else {
            fragment_MenuDescrition temp=fragment_MenuDescrition.newInstance(1);
            temp.show(fm,"temptext");
            Log.d("00","111111111111111111111111");
        }

    }
}
