package com.knopkapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

public class Registrationandverification4Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrationandverification4, container, false);
        updateProgressBar(100);

        return view;
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