package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private static final int READ_EXTERNAL_STORAGE_PERMISSION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
				READ_EXTERNAL_STORAGE_PERMISSION))
		{
			init();
		}
	}

	protected boolean askForPermission(String permission, int requestCode)
	{
		boolean granted;

		if (ContextCompat.checkSelfPermission(this, permission) !=
			PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
			ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
			granted = false;
		}
		else
		{
			granted = true;
		}

		return granted;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                      @NotNull String[] permissions,
	                                      @NotNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION)
		{
			if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
			{
				Toast.makeText(MainActivity.this,
						"Permís concedit",
						Toast.LENGTH_SHORT).show();
				init();
			}
			else {
				Toast.makeText(MainActivity.this,
						"Permís denegat!",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	protected void init()
	{
		TextView lblData = findViewById(R.id.LblData);
		ListView lstFrutas = findViewById(R.id.LstFrutas);

		try
		{
			String path = getFilesDir().getAbsolutePath() + File.separator + "frutas.json";
			lblData.setText(path);
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);

			Gson gson = new Gson();
			Fruta [] frutas = gson.fromJson(br, Fruta [].class);

			ArrayAdapter<Fruta> adapter =
					new ArrayAdapter<Fruta> (this,
													android.R.layout.simple_list_item_1,
													frutas);
			lstFrutas.setAdapter(adapter);

			fr.close();
		} catch (IOException e)
		{
			lblData.setText(e.toString());
		}
	}
}