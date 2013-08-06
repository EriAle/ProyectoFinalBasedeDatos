/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entidades.Articulo;
import entidades.Cliente;
import entidades.Pelicula;
import entidades.Usuario;
import entidades.Videojuego;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.beans.Rows;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.MultigetSliceQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;
import me.prettyprint.hector.api.query.SliceQuery;

/**
 *
 * @author ALUMNO-15
 */
public class Conexion {
    static Cluster cluster;
    static Keyspace keyspace;
    public String tabla;
    private String ip;
    private String nombreCluster;
    private String nombreKeyspace;
    static StringSerializer stringSerializer = StringSerializer.get();
    static ListIterator<Row<String, String, String>> resultado;
    static List<HColumn<String, String>> columna;
    private Iterator<HColumn<String, String>> c;
    private HColumn<String, String> dupla;

    public Conexion(String ip, String nombreCluster) {
        this.ip = ip;
        this.nombreCluster = nombreCluster;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
      

    public Keyspace abrirConexion() {
        try {
            cluster = HFactory.getOrCreateCluster("clustersito","localhost:9160");
            keyspace = HFactory.createKeyspace(nombreCluster, cluster);
            return keyspace;
        }catch (Exception ex) {
    		System.out.println("Error encontrado en conexion!!");
    	}
        return null;
    }

    public void cerrarConexion() {
        try {
            cluster.getConnectionManager().shutdown();
        }catch (Exception ex) {
    		System.out.println("Error encontrado en conexion!!");
    	}
    }
    
    public void insertarCliente(Cliente cliente) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("adeudo", cliente.getAdeudo()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("apellidos", cliente.getApellidos()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("direccion", cliente.getDireccion()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("inicio", cliente.getInicio()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("nombre", cliente.getNombre()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("telefono", cliente.getTelefono()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("tipo", cliente.getTipo()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("surcursal", cliente.getSucursal()));
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras se insertaban datos!!");
            ex.printStackTrace() ;
        }
    }
    
    public void insertarCliente(Usuario usuario) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("apellidos", usuario.getApellidos()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("direccion", usuario.getDireccion()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("cargo", usuario.getCargo()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("nombre", usuario.getNombre()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("telefono", usuario.getTelefono()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("noUsuario", usuario.getNombreUsuario()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("password", usuario.getPassword()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("surcursal", usuario.getSucursal()));
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras se insertaban datos!!");
            ex.printStackTrace() ;
        }
    }
    
    public void insertarArticulo(Articulo articulo) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.insert(articulo.getClave(), "articulo", HFactory.createStringColumn("clave", articulo.getClave()));
            mutator.insert(articulo.getClave(), "articulo", HFactory.createStringColumn("precio", String.valueOf(articulo.getPrecio())));
            mutator.insert(articulo.getClave(), "articulo", HFactory.createStringColumn("descripcion", articulo.getDescripcion()));
            mutator.insert(articulo.getClave(), "articulo", HFactory.createStringColumn("tipo", "otros"));
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras se insertaban datos!!");
            ex.printStackTrace() ;
        }
    }
    public void insertarPeliculas(Pelicula pelicula) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("clave", pelicula.getClave()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("precio", String.valueOf(pelicula.getPrecio())));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("descripcion", pelicula.getDescripcion()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("tipo", "pelicula"));
            
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("titulo", pelicula.getTitulo()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("clasificacion", pelicula.getClasificacion()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("genero", pelicula.getGenero()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("formato", pelicula.getFormato()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("venta", pelicula.getVenta()));
            
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("direccion", pelicula.getDireccion()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("año", pelicula.getAño()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("pais", pelicula.getPais()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("idioma", pelicula.getIdioma()));
            mutator.insert(pelicula.getClave(), "pelicula", HFactory.createStringColumn("subtitulos", pelicula.getSubtitulos()));
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras se insertaban datos!!");
            ex.printStackTrace() ;
        }
    }
    
    public void insertarVideojuegos(Videojuego videojuego) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("clave", videojuego.getClave()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("precio", String.valueOf(videojuego.getPrecio())));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("descripcion", videojuego.getDescripcion()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("tipo", "videojuego"));
            
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("titulo", videojuego.getTitulo()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("clasificacion", videojuego.getClasificacion()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("genero", videojuego.getGenero()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("formato", videojuego.getFormato()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("venta", videojuego.getVenta()));
            
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("plataforma", videojuego.getPlataforma()));
            mutator.insert(videojuego.getClave(), "videojuego", HFactory.createStringColumn("noJugadores", videojuego.getNoJugadores()));
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras se insertaban datos!!");
            ex.printStackTrace() ;
        }
    }
    
    public void borrarCliente(Cliente cliente) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.delete(cliente.getAdeudo(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getApellidos(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getDireccion(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getInicio(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getNombre(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getTelefono(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getTipo(),"clientes",null, stringSerializer);
            mutator.delete(cliente.getSucursal(),"clientes",null, stringSerializer);
            System.out.println("Datos borrados!!");
    	    SliceQuery<String, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
    	    sliceQuery.setColumnFamily("cliente").setKey(cliente.getClave());
    	    sliceQuery.setRange("", "", false, 4);
    	       
    	    QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute(); 
    	    System.out.println("\nTrying to Retrieve data after deleting the key 'sample':\n" + result.get());
    	    cluster.getConnectionManager().shutdown();
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras borraba datos!!");
            ex.printStackTrace() ;
	}
    }
    
    public void borrarArticulo(Articulo articulo) {
        try {
            abrirConexion();
            
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.delete(String.valueOf(articulo.getPrecio()),"articulo",null, stringSerializer);
            mutator.delete(articulo.getDescripcion(),"articulo",null, stringSerializer);
            mutator.delete(articulo.getTipo(),"articulo",null, stringSerializer);
            
            System.out.println("Datos borrados!!");
    	    SliceQuery<String, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
    	    sliceQuery.setColumnFamily("articulo").setKey(articulo.getClave());
    	    sliceQuery.setRange("", "", false, 4);
    	       
    	    QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute(); 
    	    System.out.println("\nTrying to Retrieve data after deleting the key 'sample':\n" + result.get());
    	    cluster.getConnectionManager().shutdown();
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras borraba datos!!");
            ex.printStackTrace() ;
	}
    }
    
    public void borrarPelicula(Pelicula pelicula) {
        try {
            abrirConexion();           
            
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.delete(String.valueOf(pelicula.getPrecio()),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getDescripcion(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getTipo(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getTitulo(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getClasificacion(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getGenero(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getFormato(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getVenta(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getDireccion(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getAño(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getPais(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getIdioma(),"pelicula",null, stringSerializer);
            mutator.delete(pelicula.getSubtitulos(),"pelicula",null, stringSerializer);
            
            System.out.println("Datos borrados!!");
    	    SliceQuery<String, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
    	    sliceQuery.setColumnFamily("pelicula").setKey(pelicula.getClave());
    	    sliceQuery.setRange("", "", false, 4);
    	    QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute(); 
    	    System.out.println("\nTrying to Retrieve data after deleting the key 'sample':\n" + result.get());
    	    cluster.getConnectionManager().shutdown();
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras borraba datos!!");
            ex.printStackTrace() ;
	}
    }
    
    public void borrarVideojuego(Videojuego videojuego) {
        try {
            abrirConexion();
            
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            mutator.delete(String.valueOf(videojuego.getPrecio()),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getDescripcion(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getTipo(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getClasificacion(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getGenero(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getFormato(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getVenta(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getPlataforma(),"videojuego",null, stringSerializer);
            mutator.delete(videojuego.getNoJugadores(),"videojuego",null, stringSerializer);
            
            System.out.println("Datos borrados!!");
    	    SliceQuery<String, String, String> sliceQuery = HFactory.createSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
    	    sliceQuery.setColumnFamily("videojuego").setKey(videojuego.getClave());
    	    sliceQuery.setRange("", "", false, 4);
    	       
    	    QueryResult<ColumnSlice<String, String>> result = sliceQuery.execute(); 
    	    System.out.println("\nTrying to Retrieve data after deleting the key 'sample':\n" + result.get());
    	    cluster.getConnectionManager().shutdown();
        } catch (Exception ex) {
            System.out.println("Error encontrado mientras borraba datos!!");
            ex.printStackTrace() ;
	}
    }
    
    public void modificarCliente(Cliente cliente) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("adeudo", cliente.getAdeudo()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("apellidos", cliente.getApellidos()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("direccion", cliente.getDireccion()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("inicio", cliente.getInicio()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("nombre", cliente.getNombre()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("telefono", cliente.getTelefono()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("tipo", cliente.getTipo()));
            mutator.insert(cliente.getClave(), "clientes", HFactory.createStringColumn("surcursal", cliente.getSucursal()));
            
            MultigetSliceQuery<String, String, String> multigetSliceQuery = HFactory.createMultigetSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
            multigetSliceQuery.setColumnFamily("clientes");
            multigetSliceQuery.setKeys(cliente.getClave());

            multigetSliceQuery.setRange("adeudo", "", false, 1);
            multigetSliceQuery.setRange("apellidos", "", false, 1);
            multigetSliceQuery.setRange("direccion", "", false, 1);
            multigetSliceQuery.setRange("inicio", "", false, 1);
            multigetSliceQuery.setRange("nombre", "", false, 1);
            multigetSliceQuery.setRange("telefono", "", false, 1);
            multigetSliceQuery.setRange("tipo", "", false, 1);
            multigetSliceQuery.setRange("sucursal", "", false, 1);
            
            QueryResult<Rows<String, String, String>> result = multigetSliceQuery.execute();
            System.out.println("Datos actulizados..." +result.get());
    		
    	} catch (Exception ex) {
    		System.out.println("Error encontrada mientras se actualizaban datos!!");
    		ex.printStackTrace() ;
    	}
    }
    
    public void modificarUsuario(Usuario usuario) {
        try {
            abrirConexion();
            Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
            
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("apellidos", usuario.getApellidos()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("direccion", usuario.getDireccion()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("cargo", usuario.getCargo()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("nombre", usuario.getNombre()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("telefono", usuario.getTelefono()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("noUsuario", usuario.getNombreUsuario()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("password", usuario.getPassword()));
            mutator.insert(usuario.getClave(), "usuario", HFactory.createStringColumn("surcursal", usuario.getSucursal()));
            
            MultigetSliceQuery<String, String, String> multigetSliceQuery = HFactory.createMultigetSliceQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
            multigetSliceQuery.setColumnFamily("usuario");
            multigetSliceQuery.setKeys(usuario.getClave());

            multigetSliceQuery.setRange("apellidos", "", false, 1);
            multigetSliceQuery.setRange("direccion", "", false, 1);
            multigetSliceQuery.setRange("cargo", "", false, 1);
            multigetSliceQuery.setRange("nombre", "", false, 1);
            multigetSliceQuery.setRange("telefono", "", false, 1);
            multigetSliceQuery.setRange("noUsuario", "", false, 1);
            multigetSliceQuery.setRange("password", "", false, 1);
            multigetSliceQuery.setRange("sucursal", "", false, 1);
            
            QueryResult<Rows<String, String, String>> result = multigetSliceQuery.execute();
            System.out.println("Datos actulizados..." +result.get());
    		
    	} catch (Exception ex) {
    		System.out.println("Error encontrada mientras se actualizaban datos!!");
    		ex.printStackTrace() ;
    	}
    }
    
    public void guardarCliente(){
        Cliente cliente1[];
        cliente1=new Cliente[100];
        abrirConexion();
        RangeSlicesQuery<String,String,String> rangeSlicesQuery= HFactory.createRangeSlicesQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
        rangeSlicesQuery.setColumnFamily("clientes");
        rangeSlicesQuery.setKeys("","");
        rangeSlicesQuery.setRange("","", false, 100);
        QueryResult<OrderedRows<String, String, String>> result= rangeSlicesQuery.execute();
        resultado = result.get().getList().listIterator();
         while(resultado.hasNext()){
              Row<String, String, String> r = resultado.next();
             ColumnSlice<String, String> columnas = r.getColumnSlice();
             columna = columnas.getColumns();
             c = columna.iterator();
             for(int i=0;i<100;i++){
                  cliente1[i]=new Cliente();
                while(c.hasNext()){
                    dupla = c.next();
                     if(dupla.getName().equals("adeudo")){ 
                         cliente1[i].setAdeudo(dupla.getValue());
                     }
                        if(dupla.getName().equals("apellidos")){
                            cliente1[i].setApellidos(dupla.getValue());
                        }
                            if(dupla.getName().equals("direccion")){
                                cliente1[i].setDireccion(dupla.getValue());
                            }
                                if(dupla.getName().equals("inicio")){
                                    cliente1[i].setInicio(dupla.getValue());
                                }
                                    if(dupla.getName().equals("nombre")){
                                        cliente1[i].setNombre(dupla.getValue());
                                    }
                                        if(dupla.getName().equals("telefono")){
                                            cliente1[i].setTelefono(dupla.getValue());
                                        }
                                            if(dupla.getName().equals("tipo")){
                                                cliente1[i].setTipo(dupla.getValue());
                                            }
                                                if(dupla.getName().equals("sucursal")){
                                                    cliente1[i].setSucursal(dupla.getValue());
                                                }
                  }
             }
        }
    }
    
    public Usuario [] guardaUsuario(){
        Usuario[] usu=new Usuario[100];
        abrirConexion();
        RangeSlicesQuery<String,String,String> rangeSlicesQuery= HFactory.createRangeSlicesQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
        rangeSlicesQuery.setColumnFamily("usuario");
        rangeSlicesQuery.setKeys("","");
        rangeSlicesQuery.setRange("","", false, 100);
        QueryResult<OrderedRows<String, String, String>> result= rangeSlicesQuery.execute();
        resultado = result.get().getList().listIterator();
         while(resultado.hasNext()){
             Row<String, String, String> r = resultado.next();
             ColumnSlice<String, String> columnas = r.getColumnSlice();
             columna = columnas.getColumns();
             c = columna.iterator();
             for(int i=0;i<100;i++){
                 usu[i]=new Usuario();
                 while(c.hasNext()){
                     dupla = c.next();
                     if(dupla.getName().equals("apellidos")){
                         usu[i].setApellidos(dupla.getValue());
                     }
                         if(dupla.getName().equals("direccion")){
                             usu[i].setDireccion(dupla.getValue());
                         }
                             if(dupla.getName().equals("cargo")){
                                 usu[i].setCargo(dupla.getValue());
                             }
                                 if(dupla.getName().equals("nombre")){
                                     usu[i].setNombre(dupla.getValue());
                                 }
                                     if(dupla.getName().equals("telefono")){
                                         usu[i].setTelefono(dupla.getValue());
                                     }
                                         if(dupla.getName().equals("noUsuario")){
                                             usu[i].setNombreUsuario(dupla.getValue());
                                         }
                                             if(dupla.getName().equals("password")){
                                                 usu[i].setPassword(dupla.getValue());
                                             }
                                                 if(dupla.getName().equals("sucursal")){
                                                     usu[i].setSucursal(dupla.getValue());
                                                 }
                }
            }
       }
         return usu;
    }
    
    
    public void guardarArticulo(){
        Articulo[] articulo=new Articulo[100];
        abrirConexion();
        RangeSlicesQuery<String,String,String> rangeSlicesQuery= HFactory.createRangeSlicesQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
        rangeSlicesQuery.setColumnFamily("articulo");
        rangeSlicesQuery.setKeys("","");
        rangeSlicesQuery.setRange("","", false, 100);
        QueryResult<OrderedRows<String, String, String>> result= rangeSlicesQuery.execute();
        resultado = result.get().getList().listIterator();
         while(resultado.hasNext()){
             Row<String, String, String> r = resultado.next();
             ColumnSlice<String, String> columnas = r.getColumnSlice();
             columna = columnas.getColumns();
             c = columna.iterator();
             for(int i=0;i<100;i++){
                 articulo[i]=new Articulo();
                 while(c.hasNext()){
                     dupla = c.next();
                     if(dupla.getName().equals("clave")){
                         articulo[i].setClave(dupla.getValue());
                     }
                     if(dupla.getName().equals("precio")){
                         articulo[i].setPrecio(Float.parseFloat(dupla.getValue()));
                     }
                     if(dupla.getName().equals("descripcion")){
                         articulo[i].setDescripcion(dupla.getValue());
                     }
                     if(dupla.getName().equals("tipo")){
                         articulo[i].setTipo(dupla.getValue());
                     }
                 }
             }
         }
    }
    
    public void guardarPelicula(){
        Pelicula[] pelicula=new Pelicula[100];
        abrirConexion();
        RangeSlicesQuery<String,String,String> rangeSlicesQuery= HFactory.createRangeSlicesQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
        rangeSlicesQuery.setColumnFamily("pelicula");
        rangeSlicesQuery.setKeys("","");
        rangeSlicesQuery.setRange("","", false, 100);
        QueryResult<OrderedRows<String, String, String>> result= rangeSlicesQuery.execute();
        resultado = result.get().getList().listIterator();
        while(resultado.hasNext()){
            Row<String, String, String> r = resultado.next();
            ColumnSlice<String, String> columnas = r.getColumnSlice();
            columna = columnas.getColumns();
            c = columna.iterator();
            for(int i=0;i<100;i++){
                pelicula[i]=new Pelicula();
                while(c.hasNext()){
                    dupla = c.next();
                    if(dupla.getName().equals("clave")){
                        pelicula[i].setClave(dupla.getValue());
                    }
                    if(dupla.getName().equals("precio")){
                        pelicula[i].setPrecio(Float.parseFloat(dupla.getValue()));
                    }
                    if(dupla.getName().equals("descripcion")){
                        pelicula[i].setDescripcion(dupla.getValue());
                    }
                    if(dupla.getName().equals("tipo")){
                        pelicula[i].setTipo(dupla.getValue());
                    }
                    if(dupla.getName().equals("titulo")){
                        pelicula[i].setTitulo(dupla.getValue());
                    }
                    if(dupla.getName().equals("clasificacion")){
                        pelicula[i].setClasificacion(dupla.getValue());
                    }
                    if(dupla.getName().equals("genero")){
                        pelicula[i].setGenero(dupla.getValue());
                    }
                    if(dupla.getName().equals("formato")){
                        pelicula[i].setFormato(dupla.getValue());
                    }
                    if(dupla.getName().equals("venta")){
                        pelicula[i].setVenta(dupla.getValue());
                    }
                    if(dupla.getName().equals("direccion")){
                        pelicula[i].setDireccion(dupla.getValue());
                    }
                    if(dupla.getName().equals("año")){
                        pelicula[i].setAño(dupla.getValue());
                    }
                    if(dupla.getName().equals("pais")){
                        pelicula[i].setPais(dupla.getValue());
                    }
                    if(dupla.getName().equals("idioma")){
                        pelicula[i].setIdioma(dupla.getValue());
                    }
                    if(dupla.getName().equals("subtitulos")){
                        pelicula[i].setSubtitulos(dupla.getValue());
                    }
                }
            }
        }
    }
    
    public void guardaVideojuego(){
        Videojuego[] video=new Videojuego[100];
        abrirConexion();
        RangeSlicesQuery<String,String,String> rangeSlicesQuery= HFactory.createRangeSlicesQuery(keyspace, stringSerializer, stringSerializer, stringSerializer);
        rangeSlicesQuery.setColumnFamily("videojuego");
        rangeSlicesQuery.setKeys("","");
        rangeSlicesQuery.setRange("","", false, 100);
        QueryResult<OrderedRows<String, String, String>> result= rangeSlicesQuery.execute();
        resultado = result.get().getList().listIterator();
        while(resultado.hasNext()){
            Row<String, String, String> r = resultado.next();
            ColumnSlice<String, String> columnas = r.getColumnSlice();
            columna = columnas.getColumns();
            c = columna.iterator();
            for(int i=0;i<100;i++){
                video[i]=new Videojuego();
                while(c.hasNext()){
                    dupla = c.next();
                    if(dupla.getName().equals("clave")){
                        video[i].setClave(dupla.getValue());
                    }
                    if(dupla.getName().equals("precio")){
                        video[i].setPrecio(Float.parseFloat(dupla.getValue()));
                    }
                    if(dupla.getName().equals("descripcion")){
                        video[i].setDescripcion(dupla.getValue());
                    }
                    if(dupla.getName().equals("tipo")){
                        video[i].setTipo(dupla.getValue());
                    }
                    if(dupla.getName().equals("titulo")){
                        video[i].setTitulo(dupla.getValue());
                    }
                    if(dupla.getName().equals("clasificacion")){
                        video[i].setClasificacion(dupla.getValue());
                    }
                    if(dupla.getName().equals("genero")){
                        video[i].setGenero(dupla.getValue());
                    }
                    if(dupla.getName().equals("formato")){
                        video[i].setFormato(dupla.getValue());
                    }
                    if(dupla.getName().equals("venta")){
                        video[i].setVenta(dupla.getValue());
                    }
                    if(dupla.getName().equals("plataforma")){
                        video[i].setPlataforma(dupla.getValue());
                    }
                    if(dupla.getName().equals("noJugadores")){
                        video[i].setNoJugadores(dupla.getValue());
                    }
                }
            }
        }
    }
}
