package com.haodemon.tasks.Behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Allows snackbar to move layouts upwards when appearing on screen
 * */
public class MoveUpwardBehavior extends CoordinatorLayout.Behavior<View> {
    public MoveUpwardBehavior() {
        super();
    }

    public MoveUpwardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
                                   @NonNull View child,
                                   @NonNull View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent,
                                          @NonNull View child,
                                          @NonNull View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());

        // Dismiss last SnackBar immediately to prevent from conflict when showing SnackBars immediately after each other
        ViewCompat.animate(child).cancel();

        // Move entire child layout up that causes objects on top disappear
        child.setTranslationY(translationY);

        // Set top padding to child layout to reappear missing objects
        // If you had set padding to child in xml, then you have to set them here by <child.getPaddingLeft(), ...>
        child.setPadding(0, -Math.round(translationY), 0, 0);
        return true;
    }

    @Override
    public void onDependentViewRemoved(@NonNull CoordinatorLayout parent,
                                       @NonNull View child,
                                       @NonNull View dependency) {
        //Reset paddings and translationY to its default
        child.setPadding(0, 0, 0, 0);
        ViewCompat.animate(child).translationY(0).start();
    }
}