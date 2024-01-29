package com.knopkapp;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.knopkapp.activities.RegistrationActivity;
import com.knopkapp.activities.WriteSMSFragment;

public class Registrationandverification1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrationandverification1, container, false);

        updateProgressBar(20);

        Button nextButton = view.findViewById(R.id.regnext1);
        nextButton.setOnClickListener(v -> replaceFragment(new WriteSMSFragment()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateProgressBar(int progress) {
        Activity activity = getActivity();
        if (activity != null) {
            ProgressBar progressBar = activity.findViewById(R.id.progressBar);

            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), progress);
            animation.setDuration(1000);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.start();
        }
    }
}
