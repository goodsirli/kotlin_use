package com.example.kotlinfirstdemo.recyclertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kotlinfirstdemo.R;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxTextView;

public class RecyclerTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
    }
}