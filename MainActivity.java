package com.example.abc.cmart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseData;
    private List<Products> products;
    private int j;
    private String mystring;
    private String[] Name;
    private ArrayList<String> mylist;
    private FirebaseUser user;
    private ProgressDialog progressDialog;
    private String type = "Book";
    private RecyclerAdafter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseData = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        setupUIViews();
        initToolbar();
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Wait while we load the Products for you!");
        progressDialog.show();


        ApiInterface apiInterface = RetrofitClient.getApiClient().create(ApiInterface.class);
        Call<List<Products>> call = apiInterface.showProduct();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {


                products = response.body();
                adapter = new RecyclerAdafter(products,MainActivity.this);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });




    }


    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarMain);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Campus Mart");
    }


        private void Logout() {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case (R.id.item1): {
                    startActivity(new Intent(MainActivity.this, AddActivity.class));
                    break;
                }
                case (R.id.item2): {
                    break;
                }
                case (R.id.item3): {
                    break;
                }
                case (R.id.item4): {
                    Logout();
                    break;

                }
            }
            return super.onOptionsItemSelected(item);
        }
    }
