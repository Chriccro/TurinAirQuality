# TurinAirQuality
**Programma che mostra l'andamento delle temperature della regione piemonte dal 1991 al 2017**

>Per visualizzare il contenuto del programma scaricare il pacchetto jar e avviare il file Temperature.jar 

*Abbiamo utilizzato un semplice metodo di lettura da file csv:*
  
  ```java
  String line = "";
  String cvsSplit = ";";


  try (BufferedReader br = new BufferedReader(new FileReader(file))) {

   while ((line = br.readLine()) != null) {

    String[] temperature = line.split(cvsSplit);

    float inverno = Float.parseFloat(temperature[1]);
    float primavera = Float.parseFloat(temperature[2]);
    float estate = Float.parseFloat(temperature[3]);
    float autunno = Float.parseFloat(temperature[4]);

    Temperature t = new Temperature(temperature[0], inverno, primavera, estate, autunno);

    txt.append(t.toString());

   }
  } catch (IOException e) {
   JFrame errorFrame = new JFrame();
   JOptionPane.showMessageDialog(errorFrame,
    "Scegli un file valido",
    "Errore",
    JOptionPane.ERROR_MESSAGE);
  }
  ```
  *Per i grafici abbiamo utilizzato una libreria chiamata JFreeChart per il grafico lineare e java awt per il grafico a barre*
  
