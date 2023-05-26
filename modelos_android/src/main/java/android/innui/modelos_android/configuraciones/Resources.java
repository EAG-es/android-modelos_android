/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.innui.modelos_android.configuraciones;

import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_internacionalizacion;
import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_recursos;
import static android.innui.modelos_android.configuraciones.rutas.crear_ruta_desde_clase;

import innui.bases;
import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class Resources extends bases {
    public static String k_in_ruta = "assets/in/android/innui/modelos/configuraciones/in";
    /**
     * Obtiene un recurso
     * @param ruta_relativa ruta del recurso
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResource(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(Class<?> clase, String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResource(clase, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        return getResource(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return la URL del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static URL getResource(Class<?> clase, String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        File file = null;
        ResourceBundle in;
        URL url = null;
        String ruta;
        try {
            ruta = crear_ruta_desde_clase(clase, ruta_relativa, ok);
            ok.es = (ruta != null);
            if (ok.es) {
                file = new File(ruta);
                ok.es = file.exists();
            }
            if (ok.es) {
                url = file.toURI().toURL();
            } else {
                url = clase.getResource(ruta_relativa);
                ok.es = (url != null);
            }
        } catch (Exception e) {
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "NO SE HA ENCONTRADO LA URL DEL RECURSO SOLICITADO. ")
                , ok.txt);
            ok.setTxt(ok.getTxt(), e);
            url = null;
        }
        return url;
    }
    /**
     * Obtiene un recurso como un stream
     * @param ruta_relativa ruta del recurso
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResourceAsStream(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(Class<?> clase, String ruta_relativa) throws Exception {
        oks ok = new oks();
        return getResourceAsStream(clase, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        return getResourceAsStream(Resources.class, ruta_relativa, ok);
    }
    /**
     * Obtiene un recurso como un stream
     * @param clase clase desde la que referir la ruta
     * @param ruta_relativa ruta del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el InpupStream del recurso
     * @throws Exception Opción de notificar errores de excepción
     */
    public static InputStream getResourceAsStream(Class<?> clase, String ruta_relativa, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        File file = null;
        ResourceBundle in;
        String ruta;
        InputStream inputstream = null;
        try {
            ruta = crear_ruta_desde_clase(clase, ruta_relativa, ok);
            ok.es = (ruta != null);
            if (ok.es) {
                file = new File(ruta);
                ok.es = file.exists();
            }
            if (ok.es) {
                inputstream = file.toURI().toURL().openStream();
            } else {
                inputstream = clase.getResourceAsStream(ruta_relativa);
                if (ruta_relativa.startsWith(k_ruta_relativa_recursos)
                        || ruta_relativa.startsWith(k_ruta_relativa_internacionalizacion)) {
                    ok.iniciar();
                    recursos_modificables.instalar_fuera(clase, ruta_relativa, ok);
                }
            }
        } catch (Exception e) {
            ok.txt = e.getMessage();
            if (ok.txt == null) {
                ok.txt = "";
            }
            in = ResourceBundles.getBundle(k_in_ruta);
            ok.setTxt(tr.in(in, "NO SE HA PODIDO CONSTRUIR UNA CORRIENTE DE ENTRADA DESDE EL RECURSO SOLICITADO. ")
                , ok.txt);
            ok.setTxt(ok.getTxt(), e);
            inputstream = null;
        }
        return inputstream;
    }

    
}
