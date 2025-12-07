package org.service;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.xml.ws.Endpoint;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.example.InterfaceImpl;

import java.net.InetSocketAddress;

import java.net.InetSocketAddress;

/**
 * Listener para inicializar el servicio SOAP
 * Publica el endpoint en un HttpServer embebido
 */
public class SoapInitializer implements ServletContextListener {

    private Endpoint endpoint;
    private HttpServer httpServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\n");
        System.out.println("════════════════════════════════════════════════════════════");
        System.out.println("  [SOAP] Inicializando servicio SOAP...");
        System.out.println("════════════════════════════════════════════════════════════");

        try {
            // Crear la implementación
            InterfaceImpl implementor = new InterfaceImpl();
            System.out.println("  [SOAP] ✓ Implementación creada");

            // Crear el endpoint
            endpoint = Endpoint.create(implementor);
            System.out.println("  [SOAP] ✓ Endpoint creado");

            // Crear HttpServer embebido en puerto 8888
            // (Jetty está en 8080, así que usamos puerto diferente)
            httpServer = HttpServer.create(new InetSocketAddress(8888), 0);
            httpServer.setExecutor(null);
            System.out.println("  [SOAP] ✓ HttpServer creado en puerto 8888");

            // Crear contexto y publicar endpoint
            HttpContext context = httpServer.createContext("/ws/Hospital");
            endpoint.publish(context);
            System.out.println("  [SOAP] ✓ Endpoint publicado en /ws/Hospital");

            // Iniciar el HttpServer
            httpServer.start();
            System.out.println("  [SOAP] ✓ HttpServer iniciado");

            System.out.println("");
            System.out.println("════════════════════════════════════════════════════════════");
            System.out.println("  ✓✓✓ SERVICIO SOAP INICIADO CORRECTAMENTE ✓✓✓");
            System.out.println("════════════════════════════════════════════════════════════");
            System.out.println("");
            System.out.println("  🌐 URLs (en HttpServer embebido - Puerto 8888):");
            System.out.println("  Endpoint:  http://<ip/dns>:8888/ws/Hospital");
            System.out.println("  WSDL:      http://<ip/dns>:8888/ws/Hospital?wsdl");
            System.out.println("");
            System.out.println("════════════════════════════════════════════════════════════");
            System.out.println("\n");

        } catch (Exception e) {
            System.err.println("\n");
            System.err.println("════════════════════════════════════════════════════════════");
            System.err.println("  ✗✗✗ ERROR AL INICIALIZAR EL SERVICIO SOAP ✗✗✗");
            System.err.println("════════════════════════════════════════════════════════════");
            System.err.println("  Mensaje:  " + e.getMessage());
            System.err.println("  Tipo:     " + e.getClass().getName());
            System.err.println("");
            System.err.println("  Stack trace:");
            e.printStackTrace();
            System.err.println("════════════════════════════════════════════════════════════");
            System.err.println("\n");

            throw new RuntimeException("No se pudo inicializar SoapInitializer", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[SOAP] Deteniendo servicio SOAP...");

        try {
            if (endpoint != null) {
                endpoint.stop();
                System.out.println("[SOAP] ✓ Endpoint detenido");
            }

            if (httpServer != null) {
                httpServer.stop(0);
                System.out.println("[SOAP] ✓ HttpServer detenido");
            }

            System.out.println("[SOAP] ✓ Servicio SOAP detenido correctamente");
        } catch (Exception e) {
            System.err.println("[SOAP] ✗ Error al detener el servicio:");
            e.printStackTrace();
        }
    }
}
