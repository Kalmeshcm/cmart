package com.example.abc.cmart;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ABC on 03-02-2018.
 */

public class AddActivity extends AppCompatActivity {
    private Bundle savedInstanceState;
    private Button save;
    private DatabaseReference firedata;
    private EditText name;
    private Spinner type;
    private EditText desc;
    private EditText price;

    private StorageReference firestorage;
    private TextView tvseller, tvaddp;
    private FirebaseUser seller;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private static int productId = 100001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        firedata = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        save = (Button) findViewById(R.id.btnsave);
        type = (Spinner) findViewById(R.id.spinner);
        desc = (EditText) findViewById(R.id.etdatadesc);
        price = (EditText) findViewById(R.id.etdataprice);
        name = (EditText) findViewById(R.id.etdataname);
        tvaddp = (TextView) findViewById(R.id.tvaddp);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        save.setOnClickListener(new View.OnClickListener() {

        
            @Override
            public void onClick(View view) {
                FirebaseUser seller = FirebaseAuth.getInstance().getCurrentUser();
                String sell = seller.getEmail();
                String nname = name.getText().toString();
                String ntype = type.getSelectedItem().toString();
                String ndesc = desc.getText().toString();
                String nprice = price.getText().toString();
                productId++;
                ApiInterface apiInterface = RetrofitClient.getApiClient().create(ApiInterface.class);
                Call<Products> call = apiInterface.saveProduct(ntype, nname, ndesc, nprice, sell);
                call.enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        startActivity(new Intent(AddActivity.this, AddImage.class));
                        Toast.makeText(AddActivity.this, "Product Added Sucesfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {
                        Toast.makeText(AddActivity.this, "Server Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }



}
