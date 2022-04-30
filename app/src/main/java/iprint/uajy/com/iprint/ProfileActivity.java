package iprint.uajy.com.iprint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    SessionManager session;

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvPhone;
    private Button btnEdit;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        session     = new SessionManager(getApplicationContext());

        tvName      = findViewById(R.id.tvName);
        tvEmail     = findViewById(R.id.tvEmail);
        tvAddress   = findViewById(R.id.tvAddress);
        tvPhone     = findViewById(R.id.tvPhone);
        btnLogOut   = findViewById(R.id.btnLogOut);
        btnEdit     = findViewById(R.id.btnEdit);

        session.LogInStatus();
        HashMap<String, String> user = session.userDetail();
        String name         = user.get(SessionManager.KEY_NAME);
        final String email  = user.get(SessionManager.KEY_EMAIL);
        String address      = user.get(SessionManager.KEY_ADDRESS);
        String phone        = user.get(SessionManager.KEY_PHONE);

        tvName.setText(Html.fromHtml("" + name));
        tvEmail.setText(Html.fromHtml("" + email));
        tvAddress.setText(Html.fromHtml("" + address));
        tvPhone.setText(Html.fromHtml("" + phone));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, PopUpLogOutActivity.class));
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
