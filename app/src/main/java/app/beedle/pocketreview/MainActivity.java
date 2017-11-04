package app.beedle.pocketreview;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

    public LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Currency")
                .setIndicator("Check Currency")
                .setContent(new Intent(this, CurrencyTab.class));

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("PocketNote")
                .setIndicator("Check Notes")
                .setContent(new Intent(this, PocketNoteTab.class));

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("Location")
                .setIndicator("Find Location")
                .setContent(new Intent(this, LocationTab.class));

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalActivityManager.dispatchPause(!isFinishing());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }
}
