package android.innui.modelos_android.configuraciones;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import innui.modelos.errores.oks;
import innui.modelos.internacionalizacion.tr;
import innui.bases;

import android.os.Environment;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class recursos_modificables extends bases {

    public static String k_in_ruta = "assets/in/android/innui/modelos_android/configuraciones/in";
    /**
     * Copia un recurso localizado relativo a una clase, a una ruta de destino.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar_recurso(Class<?> clase, String ruta_origen_recurso, String ruta_destino, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            boolean ret = true;
            ResourceBundle in;
            InputStream inputStream;
            inputStream = clase.getResourceAsStream(ruta_origen_recurso);
            if (inputStream != null) {
                ret = copiar(inputStream, ruta_destino, ok);
            } else {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.txt = tr.in(in, "NO SE HA ENCONTRADO EL RECURSO SOLICITADO. ")
                        + ruta_origen_recurso;
                ret = false;
            }
            return ret;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso localizado relativo a una clase, a una ruta de destino.
     * @param clase Clase de referencia relativa
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino_relativo Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar_recurso_a_destino_relativo(Class<?> clase, String ruta_origen_recurso, String ruta_destino_relativo, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            InputStream inputStream;
            String ruta_absoluta_destino;
            inputStream = clase.getResourceAsStream(ruta_origen_recurso);
            File externo_directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(externo_directorio, iniciales.k_ruta_aplicacion);
            file = new File(file, ruta_destino_relativo);
            ruta_absoluta_destino = file.getCanonicalPath();
            ok.no_nul(ruta_absoluta_destino);
            copiar(inputStream, ruta_absoluta_destino, ok);
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia una serie de entrada en una ruta de destino
     * @param inputstream Serie de entrada
     * @param ruta_destino Ruta de destino del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean copiar(InputStream inputstream, String ruta_destino, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            ResourceBundle in;
            Path path;
            try {
                path = Paths.get(ruta_destino);
                Files.copy(inputstream, path, REPLACE_EXISTING);
            } catch (Exception e) {
                in = ResourceBundles.getBundle(k_in_ruta);
                ok.setTxt(tr.in(in, "ERROR AL COPIAR LA SERIE DE ENTRADA (PUEDE SER UN PROBLEMA CON LOS PERMISOS DE LA CARPETA). ")
                    , ok.txt);
                ok.setTxt(ok.getTxt(), e);
                ok.es = false;
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso fuera de un archivo jar (si no existe), manteniendo la misma estructura de carpetas.
     * @param clase Clase desde donde localizar los recursos
     * @param ruta_recurso Ruta relativa del recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean instalar_fuera(Class clase, String ruta_recurso, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String ruta_absoluta_destino;
            File file;
            File externo_directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            file = new File(externo_directorio, iniciales.k_ruta_aplicacion);
            file = new File(file, ruta_recurso);
            if (file.exists() == false) {
                rutas.crear_rutas_padre(file, ok);
                if (ok.es == false) { return false; }
                ruta_absoluta_destino = file.getCanonicalPath();
                copiar_recurso(clase, ruta_recurso, ruta_absoluta_destino, ok);
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
    /**
     * Copia un recurso fuera de un archivo jar (si no existe), manteniendo la misma estructura de carpetas.
     * @param clase Clase desde donde localizar los recursos
     * @param ruta_origen_recurso Ruta relativa del recurso
     * @param ruta_destino_recurso Ruta relativa desde la referencia de la clase, donde reproducir la estructura de carpetas y copiar el recurso
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return true si todo va bien
     * @throws Exception Opción de notificar errores de excepción
     */
    public static boolean instalar_fuera(Class clase, String ruta_origen_recurso, String ruta_destino_recurso, oks ok, Object ... extra_array) throws Exception {
        try {
            if (ok.es == false) { return false; }
            String ruta_absoluta_destino;
            File file;
            File externo_directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            file = new File(externo_directorio, iniciales.k_ruta_aplicacion);
            file = new File(file, ruta_origen_recurso);
            if (file.exists() == false) {
                ok.es = rutas.crear_rutas_padre(file, ok);
                if (ok.es == false) { return false; }
                ruta_absoluta_destino = file.getCanonicalPath();
                copiar_recurso(clase, ruta_origen_recurso, ruta_absoluta_destino, ok);
            }
            return ok.es;
        } catch (Exception e) {
            throw e; // Ayuda para la depuración
        }
    }
}
