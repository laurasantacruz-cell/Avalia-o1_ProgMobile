package com.example.avaliacao1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;

import com.example.avaliacao1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding =
                ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                binding.main,
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        setSupportActionBar(binding.toolbar);

        bottomNavigationView = binding.bottomNav;

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(
                                R.id.nav_host_fragment_content_main
                        );

        if (navHostFragment != null) {

            NavController navController =
                    navHostFragment.getNavController();

            appBarConfiguration =
                    new AppBarConfiguration.Builder(
                            R.id.FirstFragment,
                            R.id.SecondFragment
                    ).build();

            NavigationUI.setupActionBarWithNavController(
                    this,
                    navController,
                    appBarConfiguration
            );

            NavigationUI.setupWithNavController(
                    bottomNavigationView,
                    navController
            );
        }
    }

    // Esconde a barra inferior
    public void esconderBottomNavigation() {

        bottomNavigationView.animate()
                .translationY(bottomNavigationView.getHeight())
                .setDuration(200)
                .start();
    }

    // Mostra a barra inferior
    public void mostrarBottomNavigation() {

        bottomNavigationView.animate()
                .translationY(0)
                .setDuration(200)
                .start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.menu_main,
                menu
        );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(
                                R.id.nav_host_fragment_content_main
                        );

        boolean handled = false;

        if (navHostFragment != null) {

            NavController navController =
                    navHostFragment.getNavController();

            handled = NavigationUI.navigateUp(
                    navController,
                    appBarConfiguration
            );
        }

        return handled || super.onSupportNavigateUp();
    }
}