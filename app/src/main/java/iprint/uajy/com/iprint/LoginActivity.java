package iprint.uajy.com.iprint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText txtEmailLogin;
    private EditText txtPasswordLogin;
    private Button btnLogin;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        //Set Attribute
        txtEmailLogin       = findViewById(R.id.txtEmailLogin);
        txtPasswordLogin    = findViewById(R.id.txtPasswordLogin);
        btnLogin            = findViewById(R.id.btnLogin);

        //Set onClick
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLogin();
            }
        });
    }

    public void requestLogin(){
        if(txtEmailLogin.getText().toString().isEmpty()||
                txtPasswordLogin.getText().toString().isEmpty()){
            Toast.makeText(this, "Field can't be empty!", Toast.LENGTH_SHORT).show();
        }
        else{
            progress = new ProgressDialog(this);
            progress.setCancelable(false);
            progress.setMessage("Log you in...");
            progress.show();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit.Builder builder = new Retrofit.
                    Builder().baseUrl("http://iprintuajy.000webhostapp.com").
                    addConverterFactory(GsonConverterFactory.create(gson));

            Retrofit retrofit   = builder.build();
            API api             = retrofit.create(API.class);

            Call<LoginResponse> call = api.loginRequest(txtEmailLogin.getText().toString(),txtPasswordLogin.getText().toString());

            call.enqueue(new Callback<LoginResponse>() {
                @Override

                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response)
                {
                    if(response.body().getResponse().equals("OK"))
                    {
                        progress.dismiss();
                        session.CreateLoginSession(response.body().getName(), txtEmailLogin.getText().toString(), response.body().getAddress(), response.body().getPhone());
                        Toast.makeText(LoginActivity.this,"You are logged in.",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else if(response.body().getResponse().equals("not active"))
                    {
                        progress.dismiss();
                        Toast.makeText(LoginActivity.this, "Verify your account first!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progress.dismiss();
                        Toast.makeText(LoginActivity.this, "Your E-mail or Password is wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("TAG", t.toString());
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
