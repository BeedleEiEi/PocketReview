package app.beedle.pocketreview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;

import app.beedle.pocketreview.R;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tab);
        setToolbar();
    }

    private void setToolbar() {
        Toolbar tbMain = findViewById(R.id.tbLocation);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbMain.setNavigationIcon(getResources().getDrawable(R.drawable.ic_navigate_before_black_24px));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }
}
