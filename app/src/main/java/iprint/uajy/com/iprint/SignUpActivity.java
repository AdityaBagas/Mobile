package iprint.uajy.com.iprint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mAddress;
    private EditText mPhone;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Set attribute
        mName       = findViewById(R.id.txtNameInput);
        mEmail      = findViewById(R.id.txtEmailInput);
        mPassword   = findViewById(R.id.txtPasswordInput);
        mAddress    = findViewById(R.id.txtAddressInput);
        mPhone      = findViewById(R.id.txtPhoneInput);
        btnSignUp   = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
    }

    private void onClickRegister(){
        if(mName.getText().toString().isEmpty() || mEmail.getText().toString().isEmpty() || mPassword.getText().toString().isEmpty()
                || mAddress.getText().toString().isEmpty() || mPhone.getText().toString().isEmpty()){
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progress = new ProgressDialog(this);
            progress.setCancelable(false);
            progress.setMessage("Signing you up...");
            progress.show();

            Retrofit.Builder builder = new Retrofit
                    .Builder().baseUrl("http://iprintuajy.000webhostapp.com")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit   = builder.build();
            API api             = retrofit.create(API.class);

            Call<String> call   = api.signup(mName.getText().toString(), mEmail.getText().toString(),
                    mPassword.getText().toString(), mAddress.getText().toString(), mPhone.getText().toString());

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    startIntent();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "You are successfully signed up!", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    startIntent();
                }
            });
        }
    }

    private void startIntent(){
        Intent intent=new Intent(getApplicationContext(),SignUpConfirmActivity.class);
        startActivity(intent);
    }
}
