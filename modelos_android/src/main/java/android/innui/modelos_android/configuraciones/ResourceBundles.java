package android.innui.modelos_android.configuraciones;

import static android.innui.modelos_android.configuraciones.iniciales.k_extension_properties;
import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_internacionalizacion;
import static android.innui.modelos_android.configuraciones.iniciales.k_ruta_relativa_recursos;
import static android.innui.modelos_android.configuraciones.rutas.crear_ruta_desde_clase;

import innui.modelos.errores.oks;
import innui.bases;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class ResourceBundles extends bases {
    /**
     * Obtiene un archivo de propiedades sin lanzar excepción si falla
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @return el archivo de propiedades
     */
    public static ResourceBundle getBundle_ne(String ruta_base) {
        oks ok = new oks();
        try {
            return getBundle(ruta_base, ok);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, ok);
    }
    /**
     * Obtiene un archivo de propiedades sin lanzar excepción si falla
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        return getBundle(ruta_base, Locale.getDefault(), ok);
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param locale Información de internacionalización
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale locale) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, locale, ok);
    }
    /**
     * Obtiene un archivo de propiedades sin lanzar excepción si falla
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param locale Información de internacionalización
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale locale, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle resourceBundle = null;
        String ruta;
        File file;
        URL[] urls;
        ClassLoader loader;
        String nombre;
        try {
            file = new File(ruta_base);
            nombre = file.getName();
            if (file.isAbsolute()) {
                if (file.exists()) {
                    urls = new URL [] {file.getParentFile().toURI().toURL()};
                    loader = new URLClassLoader(urls);
                    resourceBundle = ResourceBundle.getBundle(nombre, locale, loader);
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, locale);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            } else {
                ruta = crear_ruta_desde_clase(ResourceBundles.class, ruta_base, ok);
                ok.es = (ruta != null);
                if (ok.es) {
                    file = new File(ruta);
                    if (file.exists()) {
                        urls = new URL [] {file.getParentFile().toURI().toURL()};
                        loader = new URLClassLoader(urls);
                        resourceBundle = ResourceBundle.getBundle(nombre, locale, loader);
                    } else {
                        resourceBundle = ResourceBundle.getBundle(ruta_base, locale);
                        ok.es = (resourceBundle != null);
                        if (ok.es) {
                            _instalar_fuera(ruta_base, ok, extra_array);
                            if (ok.es == false) { return null; }
                        }
                    }
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, locale);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            }
            ok.no_nul(resourceBundle);
        } catch (Exception e) {
            throw e;
        }
        return resourceBundle;
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param locale Información de internacionalización
     * @param loader Classloader que usar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale locale, ClassLoader loader, oks ok, Object ... extra_array) throws Exception {
       if (ok.es == false) { return null; }
        ResourceBundle resourceBundle = null;
        String ruta;
        File file;
        String nombre;
        try {
            file = new File(ruta_base);
            nombre = file.getName();
            if (file.isAbsolute()) {
                resourceBundle = ResourceBundle.getBundle(ruta_base, locale, loader);
            } else {
                ruta = crear_ruta_desde_clase(ResourceBundles.class, ruta_base, ok);
                ok.es = (ruta != null);
                if (ok.es == false) { return null; }
                file = new File(ruta);
                if (file.exists()) {
                    URL [] urls = new URL [] {file.getParentFile().toURI().toURL()};
                    ClassLoader loader_local = new URLClassLoader(urls);
                    resourceBundle = ResourceBundle.getBundle(nombre, locale, loader_local);
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, locale, loader);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            }
            ok.no_nul(resourceBundle);
        } catch (Exception e) {
            throw e;
        }
        return resourceBundle;
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param targetLocale Información de internacionalización
     * @param loader Classloader que usar
     * @param control ResourceBundle.Control que usar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale targetLocale, ClassLoader loader, ResourceBundle.Control control, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle resourceBundle = null;
        String ruta;
        File file;
        String nombre;
        try {
            file = new File(ruta_base);
            nombre = file.getName();
            if (file.isAbsolute()) {
                resourceBundle = ResourceBundle.getBundle(ruta_base, targetLocale, loader, control);
            } else {
                ruta = crear_ruta_desde_clase(ResourceBundles.class, ruta_base, ok);
                ok.es = (ruta != null);
                if (ok.es) {
                    file = new File(ruta);
                    if (file.exists()) {
                        URL [] urls = new URL [] {file.getParentFile().toURI().toURL()};
                        ClassLoader loader_local = new URLClassLoader(urls);
                        resourceBundle = ResourceBundle.getBundle(nombre, targetLocale, loader_local, control);
                    } else {
                        resourceBundle = ResourceBundle.getBundle(ruta_base, targetLocale, loader, control);
                        ok.es = (resourceBundle != null);
                        if (ok.es) {
                            _instalar_fuera(ruta_base, ok, extra_array);
                            if (ok.es == false) { return null; }
                        }
                    }
                }
            }
            ok.no_nul(resourceBundle);
        } catch (Exception e) {
            throw e;
        }
        return resourceBundle;
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param targetLocale Información de internacionalización
     * @param control ResourceBundle.Control que usar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale targetLocale, ResourceBundle.Control control, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle resourceBundle = null;
        String ruta;
        File file;
        URL[] urls;
        ClassLoader loader;
        String nombre;
        try {
            file = new File(ruta_base);
            nombre = file.getName();
            if (file.isAbsolute()) {
                if (file.exists()) {
                    urls = new URL [] {file.getParentFile().toURI().toURL()};
                    loader = new URLClassLoader(urls);
                    resourceBundle = ResourceBundle.getBundle(nombre, targetLocale, loader, control);
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, targetLocale, control);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            } else {
                ruta = crear_ruta_desde_clase(ResourceBundles.class, ruta_base, ok);
                ok.es = (ruta != null);
                if (ok.es) {
                    file = new File(ruta);
                    if (file.exists()) {
                        urls = new URL [] {file.getParentFile().toURI().toURL()};
                        loader = new URLClassLoader(urls);
                        resourceBundle = ResourceBundle.getBundle(nombre, targetLocale, loader, control);
                    } else {
                        resourceBundle = ResourceBundle.getBundle(ruta_base, targetLocale, control);
                        ok.es = (resourceBundle != null);
                        if (ok.es) {
                            _instalar_fuera(ruta_base, ok, extra_array);
                            if (ok.es == false) { return null; }
                        }
                    }
                }
            }
            ok.no_nul(resourceBundle);
        } catch (Exception e) {
            throw e;
        }
        return resourceBundle;
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param control ResourceBundle.Control que usar
     * @param ok Comunicar resultados
     * @param extra_array Opción de añadir parámetros en el futuro.
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, ResourceBundle.Control control, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return null; }
        ResourceBundle resourceBundle = null;
        String ruta;
        File file;
        URL[] urls;
        ClassLoader loader;
        String nombre;
        try {
            file = new File(ruta_base);
            nombre = file.getName();
            if (file.isAbsolute()) {
                if (file.exists()) {
                    urls = new URL [] {file.getParentFile().toURI().toURL()};
                    loader = new URLClassLoader(urls);
                    resourceBundle = ResourceBundle.getBundle(nombre, Locale.getDefault(), loader, control);
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, control);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            } else {
                ruta = crear_ruta_desde_clase(ResourceBundles.class, ruta_base, ok);
                ok.es = (ruta != null);
                if (ok.es) {
                    file = new File(ruta);
                    if (file.exists()) {
                        urls = new URL [] {file.getParentFile().toURI().toURL()};
                        loader = new URLClassLoader(urls);
                        resourceBundle = ResourceBundle.getBundle(nombre, Locale.getDefault(), loader, control);
                    } else {
                        resourceBundle = ResourceBundle.getBundle(ruta_base, control);
                        ok.es = (resourceBundle != null);
                        if (ok.es) {
                            _instalar_fuera(ruta_base, ok, extra_array);
                            if (ok.es == false) { return null; }
                        }
                    }
                } else {
                    resourceBundle = ResourceBundle.getBundle(ruta_base, control);
                    ok.es = (resourceBundle != null);
                    if (ok.es) {
                        _instalar_fuera(ruta_base, ok, extra_array);
                        if (ok.es == false) { return null; }
                    }
                }
            }
            ok.no_nul(resourceBundle);
        } catch (Exception e) {
            throw e;
        }
        return resourceBundle;
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param locale Información de internacionalización
     * @param loader Classloader que usar
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale locale, ClassLoader loader) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, locale, ok);
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param targetLocale Información de internacionalización
     * @param loader ClassLoader que usar
     * @param control ResourceBundle.Control que usar
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale targetLocale, ClassLoader loader, ResourceBundle.Control control) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, targetLocale, loader, control, ok);
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param targetLocale Información de internacionalización
     * @param control ResourceBundle.Control que usar
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, Locale targetLocale, ResourceBundle.Control control) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, targetLocale, control, ok);
    }
    /**
     * Obtiene un archivo de propiedades
     * @param ruta_base Ruta del archivo (sin / inicial)
     * @param control ResourceBundle.Control que usar
     * @return el archivo de propiedades
     * @throws Exception Opción de notificar errores de excepción
     */
    public static ResourceBundle getBundle(String ruta_base, ResourceBundle.Control control) throws Exception {
        oks ok = new oks();
        return getBundle(ruta_base, control, ok);
    }

    /**
     * Instala el archivo en la ruta de estándar de destino.
     * @param ruta_base
     * @param ok
     * @param extra_array
     * @return
     * @throws Exception
     */
    public static boolean _instalar_fuera(String ruta_base, oks ok, Object ... extra_array) throws Exception {
        if (ok.es == false) { return false; }
        try {
            File file = new File(ruta_base);
            ruta_base = file.getAbsolutePath();
            file = new File(k_ruta_relativa_recursos);
            String ruta_relativa_recursos = file.getAbsolutePath();
            file = new File(k_ruta_relativa_internacionalizacion);
            String ruta_relativa_internacionalizacion = file.getAbsolutePath();
            if (ruta_base.startsWith(ruta_relativa_recursos)
             || ruta_base.startsWith(ruta_relativa_internacionalizacion)) {
                if (ruta_base.endsWith(k_extension_properties) == false) {
                    ruta_base = ruta_base + k_extension_properties;
                }
                recursos_modificables.instalar_fuera(ResourceBundles.class
                  , ruta_base, ok);
            }
        } catch (Exception e) {
            ok.setTxt(e);
        }
        return ok.es;
    }

}
