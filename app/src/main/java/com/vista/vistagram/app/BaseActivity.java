package com.vista.vistagram.app;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vista.vistagram.R;

/**
 * Activity util class.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Set up a toolbar.
     */
    @SuppressWarnings("ConstantConditions")
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    /**
     * Same as Activity#findViewById, but this method doesn't need a typecasting
     * @return View
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }
}
