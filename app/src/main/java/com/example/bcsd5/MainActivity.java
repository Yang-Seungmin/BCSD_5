package com.example.bcsd5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bcsd5.board.AddPostActivity;
import com.example.bcsd5.board.BoardFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQ_ADD_POST = 1000;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private BoardFragment boardFragment;
    private CalcFragment calcFragment;
    private WebViewFragment webViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /* 게시판 메뉴를 선택하는 코드를 통해 첫 화면을 설정 */
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_toolbar, menu);
        return true;
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        boardFragment = new BoardFragment();
        calcFragment = new CalcFragment();
        webViewFragment = new WebViewFragment();

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_post:
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivityForResult(intent, REQ_ADD_POST);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_ADD_POST) {

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.item_board:
                fragmentTransaction.replace(R.id.drawer_container, boardFragment);
                break;
            case R.id.item_calc:
                fragmentTransaction.replace(R.id.drawer_container, calcFragment);
                break;
            case R.id.item_web:
                fragmentTransaction.replace(R.id.drawer_container, webViewFragment);
                break;
        }
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(item.getTitle());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
