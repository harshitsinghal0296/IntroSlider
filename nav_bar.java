package info.androidhive.introslider;

import android.app.Service;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class nav_bar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.drawer);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.play_again,R.string.play_again);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        // CALL DRAWER
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

 @SuppressWarnings("StatementWithEmptyBody")
    @Override
           public boolean onNavigationItemSelected(MenuItem item) {

     if (item.getTitle().equals("Contact Us")) {
         Intent intent = new Intent(nav_bar.this,contact.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     } else if (item.getTitle().equals("About Us")) {
         Intent intent = new Intent(nav_bar.this, About.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     } else if (item.getTitle().equals("Service Charges")) {
         Intent intent = new Intent(nav_bar.this, service.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     }else if (item.getTitle().equals("Refund And Cancellation")) {
         Intent intent = new Intent(nav_bar.this, NavCommonActivity.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     } else if (item.getTitle().equals("Terms And Condition")) {
         Intent intent = new Intent(nav_bar.this, NavCommonActivity.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     }else if (item.getTitle().equals("Privacy Policy")) {
         Intent intent = new Intent(nav_bar.this, NavCommonActivity.class);
         intent.putExtra("Title", item.getTitle());
         startActivity(intent);
     }else if (item.getTitle().equals("Logout")) {
         mAuth.signOut();
         startActivity(new Intent(nav_bar.this,MainActivity.class));
         finish();

     }

     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     drawer.closeDrawer(GravityCompat.START);
     return true;
   }
}
