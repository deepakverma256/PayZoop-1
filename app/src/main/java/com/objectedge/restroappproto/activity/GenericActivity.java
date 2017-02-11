package com.objectedge.restroappproto.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.objectedge.restroappproto.R;
import com.objectedge.restroappproto.model.Cart;

import javax.inject.Inject;

/**
 * Created by deepak.verma on 29-08-2016.
 */
public class GenericActivity extends AppCompatActivity {
    @Inject
    Cart cart;

    protected ImageView miniCartImageView;
    protected TextView miniCartItemCountView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItem cartItem = menu.findItem(R.id.action_cart);
        RelativeLayout badgeLayout = (RelativeLayout) MenuItemCompat.getActionView(cartItem);
        badgeLayout.setActivated(true);
        miniCartImageView = (ImageView) badgeLayout.findViewById(R.id.cartImage);
        miniCartItemCountView = (TextView) badgeLayout.findViewById(R.id.cartText);
        miniCartItemCountView.setText(String.valueOf(cart.products.size()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           /* case R.id.nav_camera:
                Toast.makeText(getApplicationContext(),"Camera",Toast.LENGTH_LONG);
                return true;

            case R.id.nav_gallery:
                Toast.makeText(getApplicationContext(),"Gallery",Toast.LENGTH_LONG);
                return true;*/

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
