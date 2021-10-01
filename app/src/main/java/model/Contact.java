package model;

public class Contact {



    //    @PrimaryKey(autoGenerate = true)
//    @NonNull

    private GeoLocation location;
    private String id;

    //    @ColumnInfo(name = "nombre")
    private String nombre;

    //    @ColumnInfo(name = "numero")
    private String numero;

    //    @ColumnInfo(name = "ciudad")
    private String ciudad;

    //    @ColumnInfo(name = "descripcion")
    private String descripcion;

    //    @ColumnInfo(name = "foto")
    private String foto;

    public Contact(String id, String nombre, String numero, String ciudad, String descripcion, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Contact() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto='" + foto + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
