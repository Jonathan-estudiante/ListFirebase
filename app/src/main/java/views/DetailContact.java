package views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.proyecto.R;

public class DetailContact extends AppCompatActivity {

    TextView Name, City, Description, Number;
    ImageView Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        Name = (TextView) findViewById(R.id.detailContactName);
        City = (TextView) findViewById(R.id.detailContactCity);
        Description = (TextView) findViewById(R.id.detailContactDescription);
        Image = (ImageView)  findViewById(R.id.detailContactImage);
        Number = (TextView) findViewById(R.id.detailContactNumber);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String city = intent.getStringExtra("ciudad");
        String description = intent.getStringExtra("descripcion");
        String img = intent.getStringExtra("foto");
        String number = intent.getStringExtra("numero");
        System.out.println("Imagen: " + img);
        Name.setText(nombre);
        City.setText(city);
        Description.setText(description);
        Glide.with(Image.getContext())
                .load(Uri.parse(img))
                .into(Image);
        Number.setText(number);

    }
}
