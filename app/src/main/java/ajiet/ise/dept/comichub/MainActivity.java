package ajiet.ise.dept.comichub;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.SearchView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product> productList;

    Toolbar toolbar;

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    ProductAdapter adapter;


    private AdView mAdView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        drawerLayout=findViewById(R.id.drawer);


        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    // COMMON
                    case R.id.facebookPage:
                        Intent facebook=new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
                        startActivity(facebook);
                        break;
                    case R.id.website:
                        Intent website=new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
                        startActivity(website);
                        break;
                    case R.id.nav_privacy_policy:
                        Intent privacy=new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
                        startActivity(privacy);
                        break;
                    case R.id.nav_terms_conditions:
                        Intent terms=new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
                        startActivity(terms);
                        break;
                    case R.id.more:
                        Intent more=new Intent(Intent.ACTION_VIEW, Uri.parse("www.google.com"));
                        startActivity(more);
                        break;
                    // COMMON

                    //IMPORTANT

                    case R.id.nav_rate:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+getPackageName())));
                        }
                        catch (Exception ex)
                        {
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id" +getPackageName())));
                        }
                        break;
                    case R.id.nav_share:
                        Intent shareIntent=new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"COMICHUB");
                        String shareMessage="Enter the world of Comics";
                        shareMessage=shareMessage+"http://play.google.com/store/apps/details?id"+ BuildConfig.APPLICATION_ID;
                        shareIntent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                        startActivity(Intent.createChooser(shareIntent,"Choose one"));
                        break;
                }

                return true;
            }
        });




        productList = new ArrayList<>();

        productList.add(new Product(1,"Book - 1",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 2",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 3",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 4",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 5",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 6",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 7",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));
        productList.add(new Product(1,"Book - 8",R.drawable.rohit,"https://firebasestorage.googleapis.com/v0/b/comichub-75d66.appspot.com/o/One%20Piece%20-%20CH%200001%20%40Manga_Gallery.pdf?alt=media&token=20b52ff2-ee4b-4713-8392-18a380889ecf"));


         adapter= new ProductAdapter(this,productList);
        recyclerView.setAdapter(adapter);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.rl);
        recyclerView.setAnimation(animation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem= menu.findItem(R.id.search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(s.toString());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}