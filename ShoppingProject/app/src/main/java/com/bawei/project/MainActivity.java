package com.bawei.project;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.project.base.BaseActivity;
import com.bawei.project.fragment.CricleFragment;
import com.bawei.project.fragment.HomeFragment;
import com.bawei.project.fragment.MineFragment;
import com.bawei.project.fragment.OrderFragment;
import com.bawei.project.fragment.TrolleyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator.
 * @explain
 * @time 2019/03/20 11:58.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radiogroup;
    private FrameLayout frag;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private CricleFragment cricleFragment;
    private TrolleyFragment trolleyFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;

    @Override
    protected int layoutRID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        frag = findViewById(R.id.frag);
        radiogroup = findViewById(R.id.radiogroup);
    }

    @Override
    protected void initData() {
        //获取fragment管理者
        manager = getSupportFragmentManager();
        //开启事务
        transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        cricleFragment = new CricleFragment();
        trolleyFragment = new TrolleyFragment();
        orderFragment = new OrderFragment();
        mineFragment = new MineFragment();
        //fragment添加到事务中
        transaction.add(R.id.frag, homeFragment);
        transaction.add(R.id.frag, cricleFragment);
        transaction.add(R.id.frag, trolleyFragment);
        transaction.add(R.id.frag, orderFragment);
        transaction.add(R.id.frag, mineFragment);
        //显示和隐藏
        transaction.show(homeFragment).hide(cricleFragment).hide(trolleyFragment).hide(orderFragment).hide(mineFragment);
        //提交
        transaction.commit();
        //设置第一个选中
        radiogroup.check(radiogroup.getChildAt(0).getId());
        //选中状态改变
        radiogroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.but1:
                transaction.show(homeFragment).hide(cricleFragment).hide(trolleyFragment).hide(orderFragment).hide(mineFragment);
                break;
            case R.id.but2:
                transaction.show(cricleFragment).hide(homeFragment).hide(trolleyFragment).hide(orderFragment).hide(mineFragment);
                break;
            case R.id.but3:
                transaction.show(trolleyFragment).hide(cricleFragment).hide(homeFragment).hide(orderFragment).hide(mineFragment);
                break;
            case R.id.but4:
                transaction.show(orderFragment).hide(cricleFragment).hide(trolleyFragment).hide(homeFragment).hide(mineFragment);
                break;
            case R.id.but5:
                transaction.show(mineFragment).hide(cricleFragment).hide(trolleyFragment).hide(orderFragment).hide(homeFragment);
                break;
        }
        transaction.commit();
    }
}
