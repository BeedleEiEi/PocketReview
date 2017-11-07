package app.beedle.pocketreview;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends Activity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Currency");
        //TabHost.TabSpec spec = host.newTabSpec("Currency").setIndicator("Currency").setContent(new Intent(this, CurrencyTab.class));
        spec.setContent(R.id.tab1);
        spec.setIndicator("Currency");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Note");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Note");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Location");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Location");
        host.addTab(spec);
    }

    public void goCurrency(View view) {
        Intent intent = new Intent(this, CurrencyTab.class);
        startActivity(intent);
    }
}
