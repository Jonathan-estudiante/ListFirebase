package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.ContactAdapter;
import manager.FirebaseContactManager;
import model.Contact;
import views.DetailContact;
import views.SaveContact;
import views.UpdateContact;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    private ListView list;

    private ImageView img;
    private FloatingActionButton BtnNuevo;

    private ContactAdapter listItemAdapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ArrayList<Contact> listaContactos =new ArrayList<Contact>();
    ArrayAdapter<Contact> personaArrayAdapter;
    ContactAdapter adapter;
    Contact selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listView);
        //list.setOnItemClickListener(this);

        //listaContactos = FirebaseContactManager.getInstance().getAllContacts();
        BtnNuevo = findViewById(R.id.nuevo);
        inicializarFirebase();
        listarDatos();


//        ArrayAdapter<String> adapter;
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);
//        list.setAdapter(adapter);   Save
//        ArrayList<Contact> listContact = new ArrayList<Contact>();
//        llenarDatosBD(listContact);
//
//        ContactAdapter adapter = new ContactAdapter(this, listContact);
//        list.setAdapter(adapter);
//
//        getContactos();
//        listItemAdapter=new ContactAdapter(this,listaNombres);
//        list.setAdapter(listItemAdapter);

        BtnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SaveContact.class);
                startActivity(intent);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact selectItem = (Contact) adapter.getItem(position);

                Toast toast = Toast.makeText(MainActivity.this, "Posición: " + position +", id: "+  id, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                Intent intent = new Intent(MainActivity.this, DetailContact.class);
                intent.putExtra("nombre", selectItem.getNombre());
                intent.putExtra("ciudad", selectItem.getCiudad());
                intent.putExtra("descripcion", selectItem.getDescripcion());
                intent.putExtra("foto", selectItem.getFoto());
                intent.putExtra("numero", selectItem.getNumero());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.itemEliminar:
                Contact selectItem2 = (Contact) adapter.getItem(info.position);
                Contact contact = new Contact();
                contact.setId(selectItem2.getId());
                databaseReference.child("Contact").child(contact.getId()).removeValue();
                listarDatos();
                break;

            case R.id.itemEditar:
                selectItem = (Contact) adapter.getItem(info.position);
                Intent intent = new Intent(MainActivity.this, UpdateContact.class);
                intent.putExtra("id", selectItem.getId());
                intent.putExtra("nombre", selectItem.getNombre());
                intent.putExtra("ciudad", selectItem.getCiudad());
                intent.putExtra("descripcion", selectItem.getDescripcion());
                intent.putExtra("foto", selectItem.getFoto());
                intent.putExtra("numero", selectItem.getNumero());
                startActivity(intent);
                break;

            default:break;
        }
        return true;
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarDatos(){
        databaseReference.child("Contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listaContactos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Contact contact = dataSnapshot.getValue(Contact.class);
                    listaContactos.add(contact);
                    adapter = new ContactAdapter(MainActivity.this, listaContactos);
                    list.setAdapter(adapter);
                    registerForContextMenu(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void llenarDatosCodigo(ArrayList<Contact> list){

        Contact contact1 = new Contact();
        contact1.setNombre("Luis Castro");
        contact1.setNumero("0997746084");
        contact1.setFoto("https://bit.ly/3DOEgiG");
        contact1.setCiudad("Loja");
        contact1.setDescripcion("Amigo");

        Contact contact2 = new Contact();
        contact2.setNombre("Juan Pérez");
        contact2.setNumero("0997746084");
        contact2.setFoto("https://bit.ly/3jNTqge");
        contact2.setCiudad("Quito");
        contact2.setDescripcion("Familiar");

        Contact contact3 = new Contact();
        contact3.setNombre("Pedro Fernandéz");
        contact3.setNumero("0997746084");
        contact3.setFoto("https://bit.ly/3BRdRza");
        contact3.setCiudad("Guayaquil");
        contact3.setDescripcion("Laboral");

        Contact contact4 = new Contact();
        contact4.setNombre("Luisa Benita");
        contact4.setNumero("0990827375");
        contact4.setFoto("https://bit.ly/38OOy4a");
        contact4.setCiudad("Cuenca");
        contact4.setDescripcion("Amigo");

        Contact contact5 = new Contact();
        contact5.setNombre("Billie Eilish");
        contact5.setNumero("0995634872");
        contact5.setFoto("https://bit.ly/3hbUOrr");
        contact5.setCiudad("California");
        contact5.setDescripcion("Cantante");

        list.add(contact1);
        list.add(contact2);
        list.add(contact3);
        list.add(contact4);
        list.add(contact5);

    }
}