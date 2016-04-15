package com.mxchip.activities.ibake;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mico.micosdk.MiCOUser;
import com.mxchip.callbacks.UserCallBack;
import com.mxchip.helpers.CookBookItemAdapter;
import com.mxchip.manage.ConstHelper;
import com.mxchip.manage.SetTitleBar;
import com.mxchip.manage.SharePreHelper;

/**
 * Created by Rocke on 2016/03/28.
 */
public class RecipesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String TAG = "---RecipesActivity---";

    private ListView recipes_list;
    private CookBookItemAdapter adapter;
    private SetTitleBar stb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_list);

        String recipename = (String) getIntent().getSerializableExtra("recipename");

        stb = new SetTitleBar(RecipesActivity.this);
        if (ConstHelper.checkPara(recipename))
            stb.setTitleName(recipename);
        stb.setLeftButton("back", "finish");
        stb.setRightButton("none", "");

        initView();
        initOnClick();
    }

    private void initView() {
        recipes_list = (ListView) findViewById(R.id.recipes_list);

        initDevList();

//        testCookBookList();
    }

    private void testCookBookList() {
        MiCOUser micoUser = new MiCOUser();
        int type = 2;
        String productid = "6486b2d1-0ee9-4647-baa3-78b9cbc778f7";
        SharePreHelper shareph = new SharePreHelper(RecipesActivity.this);
        String token = shareph.getData("token");
        micoUser.getCookBookList(type, productid, new UserCallBack() {
            @Override
            public void onSuccess(String message) {
                Log.d(TAG + "onFailure",  message);
            }

            @Override
            public void onFailure(int code, String message) {
                Log.d(TAG + "onFailure", code + " " + message);
            }
        }, token);
    }

    private void initOnClick() {

    }

    private void initDevList() {
        adapter = new CookBookItemAdapter(RecipesActivity.this, recipes_list);
        recipes_list.setOnItemClickListener(this);
        loadDate();
    }

    public void loadDate() {
        for (int i = 0; i < 10; i++) {
            String cb_name = "Delicious crisp bread " + i;
            String cb_img = "http://sh.sinaimg.cn/2011/1115/U5839P18DT20111115095540.jpg";
            int cb_likeno = i / 2 == 1 ? 20 + i : 25 + i;
            int cb_recipeid = i;
            adapter.addBook(cb_name, cb_img, cb_likeno + "", cb_recipeid + "");
        }
        recipes_list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}