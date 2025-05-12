package com.example.android65_dayyy2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String USER_DEFAULT = "haipt";
    public static final String PASSWORD_DEFAULT = "123456";
    private static final String TAG = "MainActivity";
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: ");
        initView();

//        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE);
//        String name = "Hai";
//        sharedPreferences.edit().putString(Constants.USE_NAME, name).apply();
//
//        Intent intent = new Intent();
//        intent.putExtra("name", name);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("name", name);
//        intent.putExtras(bundle);
        initFragments();
    }

    private void initFragments() {
        //Add Setting fragment
        SettingFragment settingFragment = SettingFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFirst, settingFragment)
                .commit();

        //Replace Second fragment
        SecondFragment secondFragment = SecondFragment.newInstance("","");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flSecond, secondFragment)
                .commit();

        getSupportFragmentManager().beginTransaction().remove(secondFragment).commit();
    }

    private void initView() {
        edtEmail = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Login");
            String userNameValue = edtEmail.getText().toString();
            String passwordValue = edtPassword.getText().toString();

            login(userNameValue, passwordValue);
        });

        btnRegister.setOnClickListener(v -> Log.d(TAG, "onClick: Register"));
    }

    private void login(String userNameValue, String passwordValue) {
        if (userNameValue.equals(USER_DEFAULT) && passwordValue.equals(PASSWORD_DEFAULT)) {
            Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("un", userNameValue);
            intent.putExtra("ps", passwordValue);
//            startActivity(intent);

            homeLauncher.launch(intent);
        } else {
            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private ActivityResultLauncher<Intent> homeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                String id = result.getData().getStringExtra("id");
                Toast.makeText(MainActivity.this, "USER WITH ID " + id + " IS LOGGED OUT", Toast.LENGTH_SHORT).show();
            }
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
}