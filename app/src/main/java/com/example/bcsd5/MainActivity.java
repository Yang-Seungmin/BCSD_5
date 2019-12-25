package com.example.bcsd5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.bcsd5.board.AddPostActivity;
import com.example.bcsd5.board.BoardFragment;
import com.example.bcsd5.board.BoardItemData;
import com.example.bcsd5.calculator.CalcFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQ_ADD_POST = 1000;

    private final int FRAGMENT_BOARD = 0;
    private final int FRAGMENT_CALC = 1;
    private final int FRAGMENT_WEBVIEW = 2;

    private Toolbar toolbar;
    private Menu menu;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Fragment[] fragments;

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
        showFragment(FRAGMENT_BOARD);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_toolbar, menu);
        this.menu = menu;
        return true;
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        fragments = new Fragment[]{
                new BoardFragment(),
                new CalcFragment(),
                new WebViewFragment()
        };

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            fragmentTransaction.add(R.id.drawer_container, fragment);
        }
        fragmentTransaction.commit();
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
        if (requestCode == REQ_ADD_POST) {
            if (resultCode == AddPostActivity.RESULT_SUCCESSFUL && data != null && data.getExtras() != null)
                ((BoardFragment) fragments[FRAGMENT_BOARD]).getBoardController().addBoardItem(new BoardItemData(
                        data.getExtras().getString("title"),
                        data.getExtras().getString("author"),
                        data.getExtras().getLong("time")
                ));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        showFragment(item.getItemId());

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(item.getTitle());

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(int itemId) {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            fragmentTransaction.hide(fragment);
        }
        switch (itemId) {
            case R.id.item_board:
                fragmentTransaction.show(fragments[FRAGMENT_BOARD]);
                showMenuItem(R.id.item_add_post);
                break;
            case R.id.item_calc:
                fragmentTransaction.show(fragments[FRAGMENT_CALC]);
                showMenuItem(R.id.item_save_calc);
                break;
            case R.id.item_web:
                fragmentTransaction.show(fragments[FRAGMENT_WEBVIEW]);
                break;
        }
        fragmentTransaction.commit();
    }

    private void showMenuItem(int... menuIds) {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(false);
        }
        for (int i = 0; i < menuIds.length; i++) {
            int menuId = menuIds[i];
            if (menu.findItem(menuId) != null)
                menu.findItem(menuId).setVisible(true);
        }
    }

    public Menu getMenu() {
        return menu;
    }
}
