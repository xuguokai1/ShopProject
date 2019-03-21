package com.bawei.project.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.project.R;

public class SearchView extends LinearLayout {

    private EditText edit_search;
    private TextView text_search, text_cancel;

    public void check(int id) {
    }

    public interface OnSearchLisenter {
        void OnSearch(String s);

    }

    public OnSearchLisenter searchLisenter;

    public void setOnSearchLisenter(OnSearchLisenter searchLisenter) {
        this.searchLisenter = searchLisenter;
    }

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //布局
        LayoutInflater.from(context).inflate(R.layout.custom_search, this);
        //找控件
        edit_search = findViewById(R.id.edit_search);
        text_search = findViewById(R.id.text_search);
        text_cancel = findViewById(R.id.text_cancel);
        text_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit_search.getText().toString();
                if (s.isEmpty()) {
                    Toast.makeText(context, "请输入您要查询的商品", Toast.LENGTH_SHORT).show();
                } else {
                    text_search.setVisibility(GONE);
                    text_cancel.setVisibility(VISIBLE);
                    if (searchLisenter != null) {
                        searchLisenter.OnSearch(s);
                    }
                }
            }
        });
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
