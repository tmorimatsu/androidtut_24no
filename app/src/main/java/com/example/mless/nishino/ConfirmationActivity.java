package com.example.mless.nishino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        FormValues formValues = getIntent().getParcelableExtra("formvalue");

        TextView name = findViewById(R.id.name_text);
        TextView sex = findViewById(R.id.sex_text);
        TextView phone = findViewById(R.id.phone_text);
        TextView hobby = findViewById(R.id.hobby_text);
        TextView work = findViewById(R.id.work_text);


        List<String> hobbies = formValues.getHobby();
        StringBuilder hobby_sb = new StringBuilder();
        hobby_sb.append("");
        if(hobbies.size() != 0){
            for(int i = 0; i < hobbies.size(); i++){
                hobby_sb.append(hobbies.get(i));
                if(i != hobbies.size()-1){
                    hobby_sb.append("、");
                }
            }
        }

        name.setText("名前:　　　　" + formValues.getSurName() + "　" + formValues.getFirstName());
        sex.setText("性別:　　　　" + formValues.getSex());
        phone.setText("電話番号:　　　　" + formValues.getPhone());
        hobby.setText("趣味:　　　　" + hobby_sb);
        work.setText("職業:　　　　" + formValues.getSurName());
    }
}
