// Importation des classes nécessaires pour la communication série avec Pi4J
import com.pi4j.io.serial.*;

// Importation des classes nécessaires pour la gestion des exceptions d'entrée/sortie et des chaînes de caractères
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

// Déclaration de la classe GPS
public class GPS {

    // Déclaration des attributs de la classe
    private final Serial serial;  // Interface pour la communication série
    private final StringBuilder receivedDataBuffer = new StringBuilder();  // Tampon pour accumuler les données reçues

    // Constructeur de la classe GPS avec gestion d'exception pour les erreurs d'entrée/sortie
    public GPS() throws IOException {
        // Initialisation de l'interface série
        this.serial = SerialFactory.createInstance();
        this.serial.open(Serial.DEFAULT_COM_PORT, 9600);  // Ouverture du port série par défaut à 9600 bauds

        // Ajout d'un écouteur d'événements pour gérer les données reçues
        this.serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                // Gestion de l'événement de réception de données
                ByteBuffer byteBuffer = null;
                String receivedData;
                try {
                    // Récupération du tampon d'octets et de la chaîne ASCII des données reçues
                    byteBuffer = event.getByteBuffer();
                    receivedData = event.getAsciiString();

                    // Traitement de chaque ligne dans les données reçues
                    String[] lines = receivedData.split("\n");
                    for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
                        String line = lines[lineIndex];

                        // Accumulation des données reçues dans le tampon
                        receivedDataBuffer.append(line);

                        // Vérification si le tampon contient une trame GPGGA complète
                        String bufferContents = receivedDataBuffer.toString();
                        if (bufferContents.contains("$GPGGA") && bufferContents.contains("*")) {
                            // Extraction et traitement de la trame GPGGA
                            processGPGGA(bufferContents);

                            // Effacement du tampon après le traitement
                            receivedDataBuffer.setLength(0);
                        }
                    }
                } catch (IOException e) {
                    // Gestion des erreurs d'entrée/sortie
                    e.printStackTrace();
                }
            }
        });
    }

    // Méthode pour recevoir les données (avec gestion des exceptions)
    public void receiveData() throws IllegalStateException, IOException {
        // Maintien du programme en cours pour recevoir les données
        System.out.println("Écoute des données GPS. Appuyez sur Entrée pour quitter.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture du port série une fois terminé
        	System.out.println("Fin -----------");
        }
    }

    // Méthode pour traiter une trame GPGGA
    private void processGPGGA(String gpggaSentence) {
       
        // Division de la trame GPGGA en champs
        String[] fields = gpggaSentence.split(",");

        // Vérification si la trame a le nombre correct de champs (13 champs pour GPGGA)
        if (fields.length == 15 && fields[0].equals("$GPGGA")) {
        	
        	//Affichage du tram :
        	 System.out.println("le Trame est : " + gpggaSentence);
        	 
            // Extraction et affichage des détails,
            System.out.println("Index de ligne : " + fields[0]);
            System.out.println("Heure : " + fields[1]);
            System.out.println("Latitude : " + fields[2] + " " + fields[3]);
            System.out.println("Longitude : " + fields[4] + " " + fields[5]);
            System.out.println("Qualité de fixation : " + fields[6]);
            System.out.println("Nombre de satellites : " + fields[7]);
            System.out.println("Dilution horizontale de la précision : " + fields[8]);
            System.out.println("Altitude : " + fields[9] + " " + fields[10]);
            System.out.println("Séparation géoïde : " + fields[12] + " " + fields[13]);
            System.out.println("------------------------------");
        }
    }

    /*// Méthode principale pour tester la classe GPS
    public static void main(String[] args) throws IOException {
        // Création d'une instance de la classe GPS
        
    	GPS gps = new GPS();
        // Démarrage de la réception des données
        gps.receiveData();
    }*/
}
