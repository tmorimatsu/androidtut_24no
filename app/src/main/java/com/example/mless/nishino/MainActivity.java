package com.example.mless.nishino;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mHobby = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: データを保存して次に使えるように？？


        //趣味周り
        setHobbyView(mHobby);
        Button select_hobby_button = findViewById(R.id.select_hobby_button);
        select_hobby_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HobbySelectionActivity.class);
                intent.putStringArrayListExtra("hobby", mHobby);
                startActivityForResult(intent, 0);
            }
        });

//        //spinnerをプログラム側で処理
//        Spinner work_spinner = findViewById(R.id.work_spinner);
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAdapter.add("item1");
//        spinnerAdapter.add("item0");
//        work_spinner.setAdapter(spinnerAdapter);

        // 利用規約周りの処理
        final TextView rule_text = findViewById(R.id.rule_text);
        final Pattern rule_pattern = Pattern.compile("利用規約");
        final String url_string = "https://google.com";
        final Linkify.MatchFilter matchFilter = new Linkify.MatchFilter() {
            @Override
            public boolean acceptMatch(CharSequence charSequence, int i, int i1) {
                return true;
            }
        };
        final Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher matcher, String s) {
                return "";
            }
        };
        Linkify.addLinks(rule_text, rule_pattern, url_string, matchFilter, transformFilter);

        // ボタンの無・有効化
        final Button doneButton = findViewById(R.id.done_button);
        final CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    doneButton.setEnabled(true);
                }else{
                    doneButton.setEnabled(false);
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPressDoneButton();
            }
        });
    }

    private void onPressDoneButton() {

        // フォームの全ての入力値を取得
        FormValues formValues = getValuesFromForm();


        if(formValues.getSurName().equals("")/*名前が入っていない場合*/ || formValues.getFirstName().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("氏名が入力されていません")
                    //.setMessage("Are you sure you want to delete this entry?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }else{
            // 別のactivityにデータを送る
            Intent confirmationIntent = new Intent(MainActivity.this, ConfirmationActivity.class);
            confirmationIntent.putExtra("formvalue", formValues);

            startActivity(confirmationIntent);

        }

    }

    private FormValues getValuesFromForm() {

        RadioGroup radioGroup = findViewById(R.id.sex_radio);
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        String sex;
        if(radioButtonId == -1){
            // 何もチェックされていないとき
            sex = "";
        }else{
            sex = ((RadioButton)findViewById(radioButtonId)).getText().toString();
        }

        final String surName = ((TextView)findViewById(R.id.surname_input)).getText().toString();
        final String firstName = ((TextView)findViewById(R.id.firstname_input)).getText().toString();
        final String phone = ((TextView)findViewById(R.id.phone_input)).getText().toString();
        final List<String> hobby = mHobby;
        final String work = ((Spinner)findViewById(R.id.work_spinner)).getSelectedItem().toString();

        FormValues formValues = new FormValues();
        formValues.setSurName(surName);
        formValues.setFirstName(firstName);
        formValues.setSex(sex);
        formValues.setPhone(phone);
        formValues.setHobby(hobby);
        formValues.setWork(work);
        return formValues;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 0){
            mHobby = data.getStringArrayListExtra("hobby");
            setHobbyView(mHobby);
        }
    }


    private void setHobbyView(List<String> hobby){
        TextView hobbyText = findViewById(R.id.hobby_view);
        StringBuilder tmp = new StringBuilder();
        tmp.append("");
        // アプリ起動時はlength==0のオブジェクトを渡す
        if(hobby.size() != 0){
            for(int i = 0; i < hobby.size(); i++){
                tmp.append(hobby.get(i));
                if(i !=  hobby.size()-1){
                    tmp.append("、");
                }
            }
        }
        hobbyText.setText(tmp);
    }
}

