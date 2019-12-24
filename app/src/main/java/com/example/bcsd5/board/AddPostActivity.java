package com.example.bcsd5.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bcsd5.R;

public class AddPostActivity extends AppCompatActivity {
    public static final int RESULT_SUCCESSFUL = 10000;

    private EditText editTextTitle, editTextAuthor;
    private Button buttonSave;

    private TextWatcher textWatcherEmptyString = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            buttonSave.setClickable(editTextTitle.length() > 0 && editTextAuthor.length() > 0);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextAuthor = findViewById(R.id.edit_text_author);

        editTextTitle.addTextChangedListener(textWatcherEmptyString);
        editTextAuthor.addTextChangedListener(textWatcherEmptyString);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title", editTextTitle.getText().toString());
                intent.putExtra("author", editTextAuthor.getText().toString());
                intent.putExtra("time", System.currentTimeMillis());
                setResult(RESULT_SUCCESSFUL, intent);

                finish();
            }
        });

        /* 버튼 비활성화 */
        textWatcherEmptyString.onTextChanged("", 0, 0, 0);
    }
}