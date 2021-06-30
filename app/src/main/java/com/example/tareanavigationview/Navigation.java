package com.example.tareanavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolBar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CircleImageView foto;
    TextView nombreUser;
    View view;
    private String userId="";
    private String user="";
    private String password="";
    private String photo="";
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Bundle bundle = this.getIntent().getExtras();
        userId=bundle.getString("UserId");
        user=bundle.getString("Usuario");
        password=bundle.getString("Password");
        photo=bundle.getString("Photo");

        mDrawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);
        toolBar=findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        navigationView.setNavigationItemSelectedListener(this);

        view=navigationView.getHeaderView(0);
        nombreUser=view.findViewById(R.id.textView_userName);
        foto=view.findViewById(R.id.profile_image);

        nombreUser.setText(bundle.getString("Usuario"));
        Glide.with(this)
                .load( bundle.getString("Photo"))
                .centerCrop()
                .into(foto);


        actionBarDrawerToggle=new ActionBarDrawerToggle(this, mDrawerLayout,toolBar,R.string.open_drawer,R.string.close_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        if (userId.equals("2")){
            Menu menu = navigationView.getMenu();
            MenuItem item = menu.getItem(0);
            item.setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (menuItem.getItemId()==R.id.nav_home){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content,new MainFragment());
            fragmentTransaction.commit();
        }
        else if (menuItem.getItemId()==R.id.nav_star){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content,new SecondFragment());
            fragmentTransaction.commit();
        }
        else if (menuItem.getItemId()==R.id.nav_grupo){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content,new TercerFragment());
            fragmentTransaction.commit();
        }
        else if (menuItem.getItemId()==R.id.camera){
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content,new CameraFragment());
            fragmentTransaction.commit();
        }
        return true;
    }
}