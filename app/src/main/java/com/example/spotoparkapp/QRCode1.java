package com.example.spotoparkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spotoparkapp.downloaders.JSONObjDownloader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class QRCode1 extends AppCompatActivity {
    TextView nome,email,bdate, spot;
    JSONObject utilizador ;
    String utilizador_name;
    //String utilizador_password;
    String utilizador_bdate;
    String utilizador_email;
    ImageView QRcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode1);

        QRcode = findViewById(R.id.imageQR2);

        JSONObjDownloader task = new JSONObjDownloader();

        try {
            utilizador = task.execute("https://spotopark-projeto.herokuapp.com/api/utilizador/" + MainActivity.USER_ID).get();
            utilizador_name = utilizador.getString("name");
            utilizador_email = utilizador.getString("email");
            utilizador_bdate = utilizador.getString("bdate");
            Log.e("",""+ utilizador_name);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String User = MainActivity.Utilizador[1] ;
        nome = (findViewById(R.id.textView32));
        email = (findViewById(R.id.textView33));
        bdate = (findViewById(R.id.textView34));
        spot = (findViewById(R.id.textView35));

       Log.e("----->",""+utilizador_name);
       Log.e("----->",""+utilizador_email);
       Log.e("----->",""+utilizador_bdate);
       Log.e("----->",""+MainActivity.Utilizador[1]);


        nome.setText((utilizador_name));
        email.setText((utilizador_email));
        bdate.setText(utilizador_bdate);
        spot.setText(User);

        String QRcode1 ="Obrigado por utilizar spotopark!\n"+"Nome: "+utilizador_name+"Email: "+utilizador_email
                +"Data de Nascimento: "+utilizador_bdate+"Lugar: "+User;

        //Inicializar a multi format writer
               MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    //Inicializar a bit matrix
                    BitMatrix matrix = writer.encode(QRcode1, BarcodeFormat.QR_CODE, 400, 400);
                    //inicializar a barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    //Inicializar bitmap
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    //Colocar o bitmap numa imageview
                    QRcode.setImageBitmap(bitmap);
                    //Inicializar o input manager
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                } catch (WriterException e) {
                    e.printStackTrace();
                }


    }

    public void onCLickGoPagamento1 (View v){
        Intent intent = new Intent(getApplicationContext(), Pagamento1.class);
        startActivity(intent);
    }
}