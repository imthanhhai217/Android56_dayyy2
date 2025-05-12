package com.example.android65_dayyy2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout;
    private String userNameValue;
    private String id = "21312312312";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initView() {
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.USE_NAME,"default");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        userNameValue = getIntent().getStringExtra("un");
        String passwordValue = getIntent().getStringExtra("ps");

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");

        tvWelcome.setText("Xin Chào " + userNameValue +"!");

    }
}