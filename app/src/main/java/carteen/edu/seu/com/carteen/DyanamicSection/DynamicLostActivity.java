package carteen.edu.seu.com.carteen.DyanamicSection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import carteen.edu.seu.com.carteen.Activity.BaseActivity;
import carteen.edu.seu.com.carteen.Model.LostInfor;
import carteen.edu.seu.com.carteen.Model.User;
import carteen.edu.seu.com.carteen.R;

/**
 * Created by Mind on 2017/10/11.
 */
public class DynamicLostActivity extends BaseActivity{

    private List<LostInfor> lostInfors=new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initData();
        init();
    }

    private void init() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//箭头的点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DyanmicLostAdapter(this,lostInfors));
    }

    private  void initData(){
        LostInfor lostInfor=new LostInfor();
        User user=new User();
        user.setImg(R.drawable.src1);
        user.setUsrNick("今日我最强");
        lostInfor.setLostuser(user);
        lostInfor.setContent("在桃园食堂丢失一把雨伞，见图，如果有看到请联系我");
        lostInfor.setTime("2017-10-10");
        lostInfors.add(lostInfor);
        lostInfors.add(new LostInfor());
        lostInfor.getImageList().clear();
        lostInfor.getImageList().add(R.drawable.umbrella1);
        lostInfor.getImageList().add(R.drawable.umbrella2);
    }

}
