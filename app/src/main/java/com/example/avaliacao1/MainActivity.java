package com.example.avaliacao1;

import android.os.Bundle;
import android.content.SharedPreferences;

import androidx.activity.EdgeToEdge;

import com.example.avaliacao1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private static final String prefs_nome = "orbis_prefs";
    private static final String key_theme = "tema_escolhido";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        applyChosenTheme();


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.FirstFragment, R.id.SecondFragment
            ).build();
            NavigationUI.setupActionBarWithNavController(
                    this, navController, appBarConfiguration);
            BottomNavigationView bottomNavigationView = binding.bottomNav;
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mostrarDialogoDeTema();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostrarDialogoDeTema() {
        String[] opcoes = {
                getString(R.string.tema_claro),
                getString(R.string.tema_escuro),
                getString(R.string.tema_sistema)
        };

        int modoAtual = getSharedPreferences(prefs_nome, MODE_PRIVATE)
                .getInt(key_theme, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        int indiceSelecionado;
        if (modoAtual == AppCompatDelegate.MODE_NIGHT_NO) {
            indiceSelecionado = 0;
        } else if (modoAtual == AppCompatDelegate.MODE_NIGHT_YES) {
            indiceSelecionado = 1;
        } else {
            indiceSelecionado = 2;
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.titulo_dialogo_tema)
                .setSingleChoiceItems(opcoes, indiceSelecionado, (dialog, which) -> {
                    int novoModo;
                    if (which == 0) {
                        novoModo = AppCompatDelegate.MODE_NIGHT_NO;
                    } else if (which == 1) {
                        novoModo = AppCompatDelegate.MODE_NIGHT_YES;
                    } else {
                        novoModo = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                    }

                    salvarTema(novoModo);
                    AppCompatDelegate.setDefaultNightMode(novoModo);

                    dialog.dismiss();
                })
                .show();
    }

    private void salvarTema(int modo) {
        SharedPreferences.Editor editor =
                getSharedPreferences(prefs_nome, MODE_PRIVATE).edit();
        editor.putInt(key_theme, modo);
        editor.apply();
    }

    private void applyChosenTheme() {
        int modoSalvo = getSharedPreferences(prefs_nome, MODE_PRIVATE)
                .getInt(key_theme, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(modoSalvo);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        boolean handled = false;
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            handled = NavigationUI.navigateUp(navController, appBarConfiguration);
        }
        return handled || super.onSupportNavigateUp();
    }

}
