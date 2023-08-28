package socket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Clase utilizada para crear el socket principal de la aplicación
 * Es la puerta de entrada para las peticiones de la aplicación.
 */
public class SocketInit {
    private final static int PUERTO_SERVIDOR = 35000;

    /**
     * Metodo que implementa la clase ServerSocket y que abre un puerto de escucha para
     * recibir peticiones externas.
     */
    public static void socketInitialize() {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO_SERVIDOR);
            System.out.println("Servidor escuchando en el puerto :"+ PUERTO_SERVIDOR);
            boolean running = true;

            while(running) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    System.out.println("Recibiendo... ");

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String response = "";
                    String inputLine, outputLine;

                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.startsWith("GET /?resource=")){
                            inputLine = inputLine.replace("GET /resource=", "");
                            inputLine = inputLine.replace(" HTTP/1.1", "");
                            response = inputLine;
                        }

                        if (!in.ready()) {
                            break;
                        }
                    }

                    outputLine = "HTTP/1.1 200 \r\n" +
                            "Content-Type: application/json \r\n" +
                            "Access-Control-Allow-Origin: * \r\n" +
                            "\r\n" +
                            getResourceByType(response);

                    out.println(outputLine);

                    out.close();
                    in.close();
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al recibir información: "+ e.getMessage());
                    System.exit(1);
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("No se puede abrir la conexión en el puerto "+ PUERTO_SERVIDOR + " : "+ e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Metodo utilizado para hacer el llamado de la clase Cache y buscar una pelicula por nombre
     * @param response Parametro que contiene el nombre de la pelicula a buscar
     * @return La respuesta con la información de la pelicula. Es agnostico de si la devolvió la caché, o el servicio externo.
     */
    private static String getResourceByType(String response) throws IOException {
        String resource = response.replace("GET /?resource=", "");
        String finalResponse = "";

        if( resource.equalsIgnoreCase("html") ){
            String htmlResponse = getFinalResponse(resource, "index");
            finalResponse = htmlResponse.replace("\"", "'");
        } else if ( resource.equalsIgnoreCase("js") ) {
            String jasResponse = getFinalResponse(resource, "index");
            finalResponse =  jasResponse.replace("\"", "'");
        } else if ( resource.equalsIgnoreCase("jpg") ){
            finalResponse =  getResponseFromImage("piolin", resource);
        } else if ( resource.equalsIgnoreCase("png") ){
            finalResponse =  getResponseFromImage("logo", resource);
        } else {
            finalResponse = getFinalResponse(resource, "style");
        }

        return "\n { \n \"status\": \"ok\", \n \"resource\": \""+ resource +"\", \n \"body\": \"" + finalResponse + " \" \n }";
    }

    private static String getResponseFromImage(String name, String resource){
        String response = "";
        byte[] imageData = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(SocketInit.class.getClassLoader().getResourceAsStream(name+"."+resource)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, resource, baos);
            imageData = baos.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
            response = Base64.getEncoder().encodeToString(byteArrayInputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println("Error encodeando la imagen a base64: "+ e.getMessage());
        }
        return response;
    }

    private static String getFinalResponse(String resource, String name) throws IOException {
        StringBuilder finalResponse = new StringBuilder();

        try(InputStream inputStream = SocketInit.class.getClassLoader().getResourceAsStream(name + "." + resource) ){

            if( inputStream != null ){
                InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String lineaArchivo;
                while ( (lineaArchivo = bufferedReader.readLine())  != null){
                    finalResponse.append(lineaArchivo);
                }
            }
        }
        return finalResponse.toString();
    }
}
