package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public interface OnItemClickCallback {
        void onItemClicked(Hero hero);
    }
    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();
    private int selectedMode = R.id.action_list; // Mode default
    private String title = "Mode List";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        rvHeroes = findViewById(R.id.rv_heroes);
        rvHeroes.setHasFixedSize(true);
        list.addAll(HeroesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(new ListHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        if (selectedMode == R.id.action_list) {
            title = "Mode List";
            showRecyclerList();
        } else if (selectedMode == R.id.action_grid) {
            title = "Mode Grid";
            showRecyclerGrid();
        } else if (selectedMode == R.id.action_cardview) {
            title = "Mode CardView";
            showRecyclerCardView();
        }
        setActionBarTitle(title);
    }


    private void updateActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    private void showRecyclerGrid(){
        rvHeroes.setLayoutManager(new GridLayoutManager(this,2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallback(new GridHeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Hero data) {
                showSelectedHero(data);
            }
        });
    }

    private void showRecyclerCardView(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHeroes.setAdapter(cardViewHeroAdapter);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedHero(Hero hero) {
        Toast.makeText(this, "Kamu memilih " + hero.getName(),Toast.LENGTH_SHORT).show();
    }

}