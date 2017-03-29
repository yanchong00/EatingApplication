package com.seu.srtp.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.seu.srtp.main.R;
import com.seu.srtp.main.activity_menu;

import java.util.List;


/**
 * Created by Mind on 2017/3/23.
 */
public class StoreRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Store> StoreList;
    private List<Menu> MenuList;
    private Context context;
    private boolean ListType;
    /*
    * 参数ItemType ==ture 代表传入的List是Store 类型的，false 代表List是Menu
    * */
    public StoreRecycleViewAdapter(List<?> list,Context context,boolean ItemType){
        ListType=ItemType;
        this.context=context;
        if(true==ItemType){
            StoreList=(List<Store>) list;
        }else{
            MenuList=(List<Menu>)list;
        }
    }

    //定义两种item的类型，适配两个布局
    private enum ITEM_TYPE{
        ITEM_STORE,
        ITEM_MENU
    }

    @Override
    public int getItemViewType(int position) {
        if(ListType==true)
        return ITEM_TYPE.ITEM_STORE.ordinal();
        else
            return  ITEM_TYPE.ITEM_MENU.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_STORE.ordinal()){
            return new StoreHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false));
        }else if(viewType == ITEM_TYPE.ITEM_MENU.ordinal()){
            return new MenuHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_maybe_delete,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d("tag",String.valueOf(position));
        if(holder instanceof StoreHolder){
            StoreHolder storeHoler=(StoreHolder)holder;
            storeHoler.store_photo.setImageResource(StoreList.get(position).getStorePhotoId());
            storeHoler.store_name.setText(StoreList.get(position).getStoreName());
            storeHoler.store_desc.setText(StoreList.get(position).getStoreDescription());

            storeHoler.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, activity_menu.class);
                    context.startActivity(intent);
                }
            });
            storeHoler.store_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    intent.putExtra(Intent.EXTRA_TEXT, StoreList.get(position).getStoreDescription());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(Intent.createChooser(intent, StoreList.get(position).getStoreName()));
                }
            });
            storeHoler.store_readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, activity_menu.class);
                    context.startActivity(intent);
                }
            });
        }
        if(holder instanceof MenuHolder){
            MenuHolder menuHolder= (MenuHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        if(ListType==true)
            return StoreList.size();
        else
            return MenuList.size();
    }

    //FirstFragment Store RecycleView ViewHolder
    public class StoreHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView store_photo;
        TextView store_name;
        TextView store_desc;
        Button store_share;
        Button store_readMore;

        public StoreHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.store_cardview);
            store_photo= (ImageView) itemView.findViewById(R.id.store_photo);
            store_name= (TextView) itemView.findViewById(R.id.store_name);
            store_desc= (TextView) itemView.findViewById(R.id.store_desc);
            store_share= (Button) itemView.findViewById(R.id.store_sharebtn);
            store_readMore= (Button) itemView.findViewById(R.id.store_morebtn);
            //描述字体加粗
            store_desc.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }
    }

    //FirstFragment Store 下的对于每一个菜品的ViewHolder
    public  class MenuHolder extends RecyclerView.ViewHolder{
//        ImageView menu_photo;
//        TextView menu_name;
//        TextView menu_desc;
//        TextView menu_price;
        public MenuHolder(View itemView) {
            super(itemView);
//            menu_photo= (ImageView) itemView.findViewById(R.id.menu_photo);
//            menu_name= (TextView) itemView.findViewById(R.id.menu_name);
//            menu_desc= (TextView) itemView.findViewById(R.id.menu_desc);
//            menu_price= (TextView) itemView.findViewById(R.id.menu_price);
        }
    }
}
