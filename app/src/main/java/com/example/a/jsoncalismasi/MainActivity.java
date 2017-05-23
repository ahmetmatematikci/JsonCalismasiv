package com.example.a.jsoncalismasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        StringBuilder stringBuilder = new StringBuilder();
        InputStream is = getResources().openRawResource(R.raw.json_verisi);

        try {
            byte buffer[] = new byte[is.available()];
            while (is.read(buffer) !=-1) ;

            String jsonVerisi = new String(buffer);
            JSONObject jsonObject = new JSONObject(jsonVerisi);
            JSONArray personel = jsonObject.getJSONArray("personel_veritabani");

            for (int i =0; i<personel.length(); i++) {
                JSONObject object = personel.getJSONObject(i);
                String ad = object.getString("adi");
                String soyad = object.getString("soyadi");
                String gorevi = object.getString("gorevi");

                stringBuilder.append("Adı : " + ad+ "\nSoyadı: " + soyad +
                "\nGörevi : " + gorevi + "\n\n");

            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textView.setText(stringBuilder);
    }

    public void btnClic(View view) { //Burada tuona tıklandığında dosya okuyor
        //File file = new File("/home/a/AndroidStudioProjects/JsonCalismasi/app/src/main/res/raw/json_verisi");//Bu hatalı
        InputStream is = getResources().openRawResource(R.raw.json_verisi);
        Scanner scanner = new Scanner(is);
        String str = "";
        while (scanner.hasNextLine()) {
            str = str + scanner.nextLine() + "\n";
        }
        textView.setText(str);


    }
}
