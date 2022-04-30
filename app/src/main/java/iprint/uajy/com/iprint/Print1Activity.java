package iprint.uajy.com.iprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Print1Activity extends AppCompatActivity{

    private Button btnCancel;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_1);

        btnCancel = findViewById(R.id.btnEditPhoto);
        btnNext = findViewById(R.id.btnNext);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Print1Activity.this, HomeActivity.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Print1Activity.this, Print2Activity.class));
            }
        });
    }
}
