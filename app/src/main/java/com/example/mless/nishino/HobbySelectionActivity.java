package com.example.mless.nishino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HobbySelectionActivity extends AppCompatActivity {

    // 設定ボタンを押さない限り登録されない仕様

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_selection);

        // ツールバー周り
        Toolbar toolbar = findViewById(R.id.toolbar_hobby_selection);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ナビゲーションアイコンクリック時の処理
                setResult(1);
                finish();
            }
        });
        // メニューのインフレート、メニューアイテムのクリック処理
        toolbar.inflateMenu(R.menu.toolbar_menu_hobby_selection);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // メニューのクリック処理
                return true;
            }
        });

        Button configButton = findViewById(R.id.config_button);
        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra( "hobby", new String[]{"te1", "te2", "te3"});
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            setResult(1);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
