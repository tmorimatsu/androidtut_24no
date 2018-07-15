package com.example.mless.nishino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        FormValues formValues = getIntent().getParcelableExtra("formvalue");
        Toast.makeText(this, formValues.getFirstName(), Toast.LENGTH_SHORT).show();

        TextView scheme = findViewById(R.id.scheme_text);
        TextView value = findViewById(R.id.value_text);

        String[] hobbies = formValues.getHobby();
        StringBuilder hobby = new StringBuilder();
        hobby.append("");
        if(hobbies.length != 0){
            for(int i = 0; i < hobbies.length; i++){
                hobby.append(hobbies[i]);
                if(i != hobbies.length-1){
                    hobby.append("、");
                }
            }
        }

        String scheme_tmp = "名前: \n\n" + "性別: \n\n" + "電話番号: \n\n"+ "趣味: \n\n" + "職業: \n\n";
        scheme.setText(scheme_tmp);
        String value_tmp = formValues.getSurName() + "　" + formValues.getFirstName() + "\n\n" + formValues.getSex() + "\n\n" + formValues.getPhone() + "\n\n" + hobby + "\n\n" + formValues.getWork() + "\n\n";
        value.setText(value_tmp);
    }
}
