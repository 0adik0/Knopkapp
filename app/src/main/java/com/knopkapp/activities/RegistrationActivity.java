package com.knopkapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.knopkapp.R;
import com.knopkapp.Registrationandverification1Fragment;
import com.knopkapp.Registrationandverification2Fragment;
import com.knopkapp.Registrationandverification3Fragment;
import com.knopkapp.Registrationandverification4Fragment;

public class RegistrationActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar = findViewById(R.id.progressBar);

        if (savedInstanceState == null) {
            setFragment(new Registrationandverification1Fragment());
        }
    }

    public void updateProgressBar(Fragment fragment) {
        if (fragment instanceof Registrationandverification1Fragment) {
            progressBar.setProgress(20);
        } else if (fragment instanceof WriteSMSFragment) {
            progressBar.setProgress(40);
        } else if (fragment instanceof Registrationandverification2Fragment) {
            progressBar.setProgress(60);
        } else if (fragment instanceof Registrationandverification3Fragment) {
            progressBar.setProgress(80);
        } else if (fragment instanceof Registrationandverification4Fragment) {
            progressBar.setProgress(100);
        }
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commitNow(); // Используйте commitNow для немедленного выполнения транзакции

        updateProgressBar(fragment); // Обновите прогресс после завершения транзакции
    }

}
