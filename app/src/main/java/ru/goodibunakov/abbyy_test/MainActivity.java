package ru.goodibunakov.abbyy_test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.goodibunakov.abbyy_test.database.DataBaseHelper;
import ru.goodibunakov.abbyy_test.database.DbSchema;
import ru.goodibunakov.abbyy_test.fragments.HistoryFragment;
import ru.goodibunakov.abbyy_test.fragments.HomeFragment;
import ru.goodibunakov.abbyy_test.model.DatabaseModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    public DataBaseHelper dBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dBHelper = new DataBaseHelper(this);
        Log.d("debug", "dBHelper.tableIsExist() =" + dBHelper.tableIsExist() );
        if (!dBHelper.tableIsExist()) {
            dBHelper.createTableCards();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return openFragment(HomeFragment.newInstance());
            case R.id.navigation_history:
                return openFragment(HistoryFragment.newInstance());
        }
        return false;
    }

    protected boolean openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.navigation_home) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        } else {
            super.onBackPressed();
        }
    }

    public void saveToDb(DatabaseModel databaseModel) {
        SQLiteDatabase db = dBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbSchema.DbTable.TEXT, databaseModel.getTitle());
        values.put(DbSchema.DbTable.INTERNATIONAL, databaseModel.getInternational());
        values.put(DbSchema.DbTable.INTERNATIONAL_VALUES, databaseModel.getInternationalValue());
        values.put(DbSchema.DbTable.TRANSLATIONS, databaseModel.getTranslations());
        values.put(DbSchema.DbTable.P2_0, databaseModel.getP2_0());
        values.put(DbSchema.DbTable.P2_1, databaseModel.getP2_1());
        values.put(DbSchema.DbTable.P2_2, databaseModel.getP2_2());
        values.put(DbSchema.DbTable.P2_3, databaseModel.getP2_3());
        db.insert(DbSchema.DbTable.TABLE_NAME, null, values);

        db.close();
    }
}
