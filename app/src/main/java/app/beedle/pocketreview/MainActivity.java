package app.beedle.pocketreview;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup {

    TabHost tabHost;
    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        TabHost host = findViewById(R.id.tabHost);
        host.setup(this.getLocalActivityManager()); //Get Activity Manager for Intent to other tabs

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Currency").setIndicator("Currency").setContent(new Intent(this, CurrencyTab.class));
        host.addTab(spec);

        //Tab 2
        TabHost.TabSpec spec2 = host.newTabSpec("Note").setIndicator("Note").setContent(new Intent(this, PocketNoteTab.class));
        host.addTab(spec2);

        //Tab 3
        spec = host.newTabSpec("Location").setIndicator("Location").setContent(new Intent(this, LocationTab.class));
        host.addTab(spec);
    }

/*    public void goCurrency(View view) { //Clicked Button
        Intent intent = new Intent(this, CurrencyTab.class);
        startActivity(intent);
    }*/

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
