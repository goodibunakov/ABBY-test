package ru.goodibunakov.abbyy_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.goodibunakov.abbyy_test.fragments.HistoryFragment;
import ru.goodibunakov.abbyy_test.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home: return openFragment(HomeFragment.newInstance());
            case R.id.navigation_history: return openFragment(HistoryFragment.newInstance());
        }
        return false;
    }

    protected boolean openFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.navigation_home) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        } else {
            super.onBackPressed();
        }
    }
}
