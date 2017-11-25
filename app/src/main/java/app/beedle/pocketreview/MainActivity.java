package app.beedle.pocketreview;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    LocalActivityManager mLocalActivityManager;
    static final int REQUEST_CODE = 1;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView pocketIcon, currencyIcon, locationIcon;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent addNoteIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(addNoteIntent, REQUEST_CODE);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        setBinding();
        setToolbar();

    }

    private void setToolbar() {
        Toolbar tbMain = findViewById(R.id.tbMain);
        setSupportActionBar(tbMain);
        getSupportActionBar().setTitle("Pocket Review");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(navigationView);
    }

    private void setBinding() {
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        pocketIcon = findViewById(R.id.pocketIcon);
        currencyIcon = findViewById(R.id.currencyIcon);
        locationIcon = findViewById(R.id.locationIcon);
    }

    public void selectItemDrawer(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.pocketTabMenu:
                intent = new Intent(MainActivity.this, PocketNoteTab.class);
                startActivity(intent);
                break;
            case R.id.currencyTabMenu:
                intent = new Intent(MainActivity.this, CurrencyTab.class);
                startActivity(intent);
                break;
            case R.id.locationMenu:
                intent = new Intent(MainActivity.this, LocationTab.class);
                startActivity(intent);
                break;
            default:
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
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

    public void changePage(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.pocketIcon:
                intent = new Intent(MainActivity.this, PocketNoteTab.class);
                startActivity(intent);
                break;
            case R.id.currencyIcon:
                intent = new Intent(MainActivity.this, CurrencyTab.class);
                startActivity(intent);
                break;
            case R.id.locationIcon:
                intent = new Intent(MainActivity.this, LocationTab.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
