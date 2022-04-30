package iprint.uajy.com.iprint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.HashMap;

public class PopUpLogOutActivity extends Activity {

    SessionManager session;

    private Button btnCancelLogOut;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_log_out);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        session = new SessionManager(getApplicationContext());
        session.LogInStatus();

        HashMap<String, String> user = session.userDetail();

        btnCancelLogOut = findViewById(R.id.btnCancelLogOut);
        btnLogOut = findViewById(R.id.btnLogOut);

        btnCancelLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                session.userLogOut();
                finish();
            }
        });
    }
}
