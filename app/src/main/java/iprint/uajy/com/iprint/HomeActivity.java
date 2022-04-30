package iprint.uajy.com.iprint;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    SessionManager session;

    private TextView txtWelcome;
    private Button btnPrint;
    private Button btnOrders;
    private Button btnHistory;
    private Button btnProfile;
    private TextView tvContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(getApplicationContext());

        txtWelcome  = findViewById(R.id.textView_welcome_name);
        btnPrint    = findViewById(R.id.btnPrint);
        btnOrders   = findViewById(R.id.btnOrders);
        btnHistory  = findViewById(R.id.btnHistory);
        btnProfile  = findViewById(R.id.btnProfile);
        tvContactUs = findViewById(R.id.tvContactUs);

        session.LogInStatus();
        HashMap<String, String> user = session.userDetail();
        String name = user.get(SessionManager.KEY_NAME);
        txtWelcome.setText(Html.fromHtml("Welcome, " + name));

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Print1Activity.class));
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OngoingOrderActivity.class));
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ContactUsActivity.class));
            }
        });
    }
}
