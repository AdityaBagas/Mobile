package iprint.uajy.com.iprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {

    private EditText txtAddress;
    private EditText txtPhone;
    private EditText txtPassword;
    private String emailExtra;
    private Button btnSave;
    private Button btnEditPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        txtAddress      = findViewById(R.id.txtAddress);
        txtPhone        = findViewById(R.id.txtPhone);
        txtPassword     = findViewById(R.id.txtPassword);
        btnSave         = findViewById(R.id.btnSave);
        btnEditPhoto       = findViewById(R.id.btnEditPhoto);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdate();
            }
        });
    }

    private void onClickUpdate(){
        Intent intent   = getIntent();
        emailExtra      = intent.getStringExtra("email");

        if(txtAddress.getText().toString().isEmpty() || txtPhone.getText().toString().isEmpty()
                || txtPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Retrofit.Builder builder = new Retrofit
                    .Builder().baseUrl("http://iprintuajy.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit   = builder.build();
            API api             = retrofit.create(API.class);
            Call<String> call   = api.edit(emailExtra, txtAddress.getText().toString(), txtPhone.getText().toString(),
                    txtPassword.getText().toString());

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(EditProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startIntent();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, "The changes will take effect after the you Log In.", Toast.LENGTH_LONG).show();
                    startIntent();
                }
            });
        }
    }

    private void startIntent(){
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }
}
