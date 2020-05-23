import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.*;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/*
**Authors: NERI Christian, NOVELLO Gabriele
**Title: Temperatura media dagll'anno 1991 al 2017
*/

public class Test extends Canvas {
 static String file = "";   //Variabile statica dove vi si inserisce il titolo del file scelto
 static boolean flag = false;   //Variabile booleana statica per il controllo degli errori relativi all'apertura del file
 public static void main(String[] args) {
  
  //Pannello e frame iniziali(menù)
  JFrame f = new JFrame("Temperatura media annuale");
  JPanel p = new JPanel();
  
  //Textarea
  JTextArea txt = new JTextArea();

  txt.setEditable(false);
  txt.setForeground(Color.WHITE);
  txt.setBackground(Color.DARK_GRAY);
  txt.setFont(new Font("Courier New", Font.ITALIC, 12));

  //Inseriamo una scrollbar alla texarea
  JScrollPane scrollbar = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

  scrollbar.setBounds(10, 50, 325, 250);
  scrollbar.setForeground(Color.WHITE);
  scrollbar.setBackground(Color.DARK_GRAY);
  scrollbar.setBorder(BorderFactory.createCompoundBorder(
   scrollbar.getBorder(),
   BorderFactory.createEmptyBorder(10, 10, 10, 10)));

  JButton sf = new JButton("Scegli file");  //Bottone per la scelta del file
  JButton dati = new JButton("Visualizza dati");  //Bottone per la visualizzazione del contenuto del file all'interno della textarea
  JButton grafico = new JButton("Istogramma");  //Bottone per la visualizzazione del grafico a barre(istogramma)
  JButton grafico2 = new JButton("Grafico lineare");  //Bottone per la visualizzazione del grafico lineare
  JButton tabella = new JButton("Visualizza tabella");  //Bottone per la visualizzazione della tabella

  //Settings del bottone relativo alla visualizzazione dei dati
  dati.setBounds(10, 310, 140, 30);
  dati.setFont(new Font("Andale Mono", Font.PLAIN, 12));
  dati.setForeground(Color.WHITE);
  dati.setBackground(Color.DARK_GRAY);
  dati.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
  
    
    
    

  //Action listener per la scelta dei file  

  sf.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    JFileChooser fc = new JFileChooser();  //Creo un filechooser 
    if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {  //Apro la finestra della scelta del file e controllo se l'utente schiaccia OK tramite un APPROVE OPTION
     file = fc.getSelectedFile().getPath();  //Salvo sulla variabile statica file il percorso del file scelto
    } else {
     JFrame errorFrame = new JFrame();          //Creo il JFrame di errore nel caso l'utente schiacci annulla nella scelta del file

     JOptionPane.showMessageDialog(errorFrame,  //Mostro la finestra di errore tramite la funzione showMessageDialog e i vari parametri che verrano mostrati all'interno del frame
      "Devi scegliere un file",
      "Errore",
      JOptionPane.ERROR_MESSAGE);
    }
    
    String line = "";
    
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {  //Leggo il file e modifico il flag che servirà nella creazione del grafico a barre

     while ((line = br.readLine()) != null) {
      flag=true;  //Flag statico viene impostato a true se è presente il file
     }
    } catch (IOException e1) {
     
    }
   }
  });

  //ActionListener per la lettura del file e la stampa sul textarea
  dati.addActionListener(new ActionListener() {  
   public void actionPerformed(ActionEvent e) {
    printCSV(txt);  //Richiamo la funzione printCSV che stampa il contenuto del file
   }
  });

  //ActionListener per la creazione della tabella
  
  tabella.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {


    String line = "";  //Variabile per la lettura di una lina del file
    String cvsSplit = ";";  //Variabile per la divisione delle parole
    int i = 0;  //Contatore per l'inserimento dei dati nell'array della tabella
    boolean flag2 = false;  //Flag per il controllo della presenza del file

    //Leggo una prima volta il file per vedere quante linee ha e incremento il contatore
    
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

     while ((line = br.readLine()) != null) {
      i++;
      flag2=true;
     }
    } catch (IOException e1) {
     
    }
    
    //Stringa per il nome delle colonne della tabella
    String[] nomec = {
     "Anni",
     "Inverno",
     "Primavera",
     "Estate",
     "Autunno"
    };
    
    //Creo il vettore a due dimensioni, la prima rappresenta la dimensione dell'array, usiamo il contatore i per dare la dimensione in base alle linee del file, 
    //il secondo parametro rappresenta quanti elementi vi sono all'interno della linea in questo caso 5
    //Ogni linea sarà rappresentata da cinque elementi quali anno, Inverno, Primavera, Esatate, Autunno
    
    Object data[][] = new Object[i][5];
    i = 0;  //Azzero il contatore



    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

     while ((line = br.readLine()) != null) {

      String[] temperature = line.split(cvsSplit); //Array di stringhe dove andrò a mettere la linea di file divisa nella varie parti

      int anno = Integer.parseInt(temperature[0]);      //Variabile dove inserire gli anni 
      float inverno = Float.parseFloat(temperature[1]);  //Variabile dove inserire le temperature invernali
      float primavera = Float.parseFloat(temperature[2]);  //Variabile dove inserire le temperature primaverili
      float estate = Float.parseFloat(temperature[3]);  //Variabile dove inserire le temperature estive
      float autunno = Float.parseFloat(temperature[4]);  //Variabile dove inserire le temperature autunnali

      //Inserisco ogni variabile all'interno dell'array laprima posizione di riferisce all'anno la seconda all'inverno la terza alla primavera la quarta all'estate e la quinta all'autunno
      
      data[i][0] = anno;        
      data[i][1] = inverno;
      data[i][2] = primavera;
      data[i][3] = estate;
      data[i][4] = autunno;

      i++;   //Incremento la posizione del vettore ad ogni lettura


     }
    } catch (IOException e1) {  
     JFrame errorFrame = new JFrame();  //JFrame per l'errore nel caso non si riesca a leggere il file
     JOptionPane.showMessageDialog(errorFrame,
      "Scegli un file valido",
      "Errore",
      JOptionPane.ERROR_MESSAGE);
    }
if(flag2==true){  //Se il file è accessibile (flag impostato a true) allora farò comparire la tabella
    

    JTable t = new JTable(data, nomec);  //Creo una nuova tabella passandogli i dati e i nomi delle celle
    t.setPreferredScrollableViewportSize(new Dimension(500, 300));  //Imposto la grandezza della tabella e la rendo scrollabile

    JScrollPane scroll = new JScrollPane(t);  //Creo un pannello scorrevole e gli aggiungo la tabella

    JFrame f = new JFrame();
    JPanel p = new JPanel();


    p.add(scroll);  //Aggiungo il pannello scorrevole al JPanel
    f.add(p);
    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    f.setSize(700, 380);
    f.setLocation(350, 200);
    f.setTitle("Temperature medie stagionali");
    f.setVisible(true);
}
   }
  });
  
  
  //Settings per il bottone relativo alla scelta del file
  sf.setBounds(10, 10, 325, 30);
  sf.setFont(new Font("Andale Mono", Font.PLAIN, 12));
  sf.setForeground(Color.WHITE);
  sf.setBackground(Color.DARK_GRAY);
  sf.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));

  
  //Settings per il bottone del grafico a barre(istogramma)
  grafico.setBounds(195, 310, 140, 30);
  grafico.setFont(new Font("Andale Mono", Font.PLAIN, 12));
  grafico.setForeground(Color.WHITE);
  grafico.setBackground(Color.DARK_GRAY);
  grafico.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));

  
  //Settings per il bottone del grafico lineare
  grafico2.setBounds(195, 350, 140, 30);
  grafico2.setFont(new Font("Andale Mono", Font.PLAIN, 12));
  grafico2.setForeground(Color.WHITE);
  grafico2.setBackground(Color.DARK_GRAY);
  grafico2.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));

  
  //Settings per il bottone della tabella
  tabella.setBounds(10, 350, 140, 30);
  tabella.setFont(new Font("Andale Mono", Font.PLAIN, 12));
  tabella.setForeground(Color.WHITE);
  tabella.setBackground(Color.DARK_GRAY);
  tabella.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));

  p.setLayout(null);
  p.setBackground(Color.BLACK);
  p.add(scrollbar);
  p.add(dati);
  p.add(grafico);
  p.add(grafico2);
  p.add(tabella);
  p.add(sf);

  f.add(p);

  f.setSize(360, 420);
  f.setResizable(false);
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  f.setVisible(true);

  
  //ActionListener per la visualizzazione del grafico a barre (istogramma)
  grafico.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
   if(flag==true){ //Verifico se è presente un file
    JFrame f = new JFrame("Grafico");

    Canvas cvs = new Test();

    cvs.setBackground(Color.DARK_GRAY);
    cvs.setSize(350, 350);
    cvs.setVisible(true);



    f.add(cvs);

    f.pack();
    f.setSize(1920, 1080);
    f.setLocation(0, 0);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    f.setVisible(true);
   
   }
   else{//Se non vi è nessun file visualizzo il messaggio di errore
       JFrame errorFrame = new JFrame();
     JOptionPane.showMessageDialog(errorFrame,
      "Scegli un file valido",
      "Errore",
      JOptionPane.ERROR_MESSAGE);
   }
      
  }
  });
  
  
  //ActionListener per la visualizzazione del grafico lineare
  grafico2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {

    String line = "";
    String cvsSplit = ";";



    try (BufferedReader br = new BufferedReader(new FileReader(file))) { //Leggo i dati da file
     DefaultCategoryDataset dataset = new DefaultCategoryDataset();  //Creo un dataset ch al suo interno conterrà tutti i punti che andranno a comporre il grafico
     while ((line = br.readLine()) != null) {

      String[] temperature = line.split(cvsSplit);

      String anno = (temperature[0].trim());
      float inverno = Float.parseFloat(temperature[1]);
      float primavera = Float.parseFloat(temperature[2]);
      float estate = Float.parseFloat(temperature[3]);
      float autunno = Float.parseFloat(temperature[4]);

      
      //Finché vi sono elementi nel file andrò ad aggiungere nel dataset i dati delle stagioni e il loro anno corrispondente tramite la funzione addValue()
      
      dataset.addValue(inverno, "Inverno", anno);  
      dataset.addValue(primavera, "Primavera", anno);
      dataset.addValue(estate, "Estate", anno);
      dataset.addValue(autunno, "Autunno", anno);




     }

     JFreeChart chart = ChartFactory.createLineChart("Temperature medie stagionali 1991-2017", "Anni", "Temperature", dataset, PlotOrientation.VERTICAL, true, true, false); //Creo il grafico e gli aggiungo il dataset
     chart.setBackgroundPaint(Color.WHITE);  //Imposto un background alla finestra del diagramma
     chart.getTitle().setPaint(Color.BLACK);  //Imposto un titolo al grafico
     LineAndShapeRenderer r = new LineAndShapeRenderer();  //Abilito le modifiche sulle linee 
     CategoryPlot p = chart.getCategoryPlot();  //Creo il panel che andrà a contenere il grafico
     p.setRenderer(r);  //Aggiungo le modifiche delle linee al pannello
     r.setSeriesPaint(0, Color.BLUE);  //Imposto i colori alle varie linee
     r.setSeriesPaint(1, Color.GREEN);
     r.setSeriesPaint(2, Color.RED);
     r.setSeriesPaint(3, Color.ORANGE);
     p.setRangeGridlinePaint(Color.WHITE);  //Aggiungo le linee orizzontali che compongono il grafico
     p.setBackgroundPaint(Color.DARK_GRAY);  //Imposto il background al pannello
     p.setOutlinePaint(Color.WHITE);  //Imposto il bordo al pannello
     p.setOutlineStroke(new BasicStroke(2));  //Do una dimensione di 2 al bordo del pannello
     JButton b = new JButton("Salva in PNG");  //Creo il bottone che mi permetterà di salvare il grafico come PNG

     ChartFrame frame = new ChartFrame("Diagramma lineare temperature", chart);  //Aggiungo il grafico al frame
     frame.setLayout(null);
     b.setBounds(600, 420, 130, 30);  //Aggiungo il bottone al frame
     frame.add(b);
     frame.setVisible(true);
     frame.setSize(800, 500);
     
     
     //ActionListener per il salvataggio su png
     b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

       JFileChooser fc = new JFileChooser();  //creo un FileChooser

       if (fc.showSaveDialog(fc) == JFileChooser.APPROVE_OPTION) { //Apro la finestra per scegliere dove salvare l'immagine e controllo che l'utente prema OK tramite l'APPROVE_OPTION
        File f = new File(fc.getSelectedFile() + ".png");  //Prendo il percorso e lo metto in una variabile File aggiungendogli .png in caso l'utente lo dimentichi

        //Imposto la grandezza dell'immagine
        int width = 800;  
        int height = 500;

        try {
         ChartUtilities.saveChartAsPNG(f, chart, width, height);  //Uso la funzione saveChartAsPNG appartenente alle ChartUtilities per salvare il grafico
        } catch (IOException ex) {
         System.err.println(ex);
        }
       }



      }
     });




    } catch (IOException e1) { //Apro la finestra di errore nel caso il file non sia valido
     JFrame errorFrame = new JFrame();
     JOptionPane.showMessageDialog(errorFrame,
      "Scegli un file valido",
      "Errore",
      JOptionPane.ERROR_MESSAGE);
    }

   }
  });


 }

 
 //Funzione per la stampa delle temperature
 static void printCSV(JTextArea txt) {

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


 }

//Grafico a barre disegnato
 public void paint(Graphics g) {

  String line = "";
  String cvsSplit = ";";
  int x = 50;
  int x1 = 10;
  int y = 190;
  int y1 = 610;


  g.setColor(Color.white);
  g.drawLine(0, 600, 1920, 600);
  
  //Asse y valori positivi
  g.setFont(new Font("Andale Mono", Font.PLAIN, 10));
  for (int i = 21; i >= 0; i--) {
   String n = String.valueOf(i);
   g.drawString(n, 30, y);
   y += 19.8;
  }
  
  //Asse y valori negativi
  for (int i = 0; i < 4; i++) {
   String n = String.valueOf(-i);
   g.drawString(n, 30, y1);
   y1 += 19.8;
  }
  g.setFont(new Font("Andale Mono", Font.BOLD, 40));
  g.setColor(Color.white);
  g.drawString("Temperatura media stagionale dall'anno 1991-2017", 500, 100);

  g.setFont(new Font("Andale Mono", Font.PLAIN, 10));
  
  //Legenda
  g.setColor(Color.blue);
  g.drawRect(20, 750, 20, 20);
  g.drawString("Inverno", 50, 765);

  g.setColor(Color.green);
  g.drawRect(20, 800, 20, 20);
  g.drawString("Primavera", 50, 815);

  g.setColor(Color.red);
  g.drawRect(20, 850, 20, 20);
  g.drawString("Estate", 50, 865);

  g.setColor(Color.orange);
  g.drawRect(20, 900, 20, 20);
  g.drawString("Autunno", 50, 915);

  Graphics2D g2d = (Graphics2D) g; //oggetto di classe Graphics2D per creare le varie barre

  //Leggo da file
  try (BufferedReader br = new BufferedReader(new FileReader(file))) {

   while ((line = br.readLine()) != null) {

    String[] temperature = line.split(cvsSplit);

    String anno = (temperature[0].trim());
    float inverno = Float.parseFloat(temperature[1]);
    float in = 600 - (inverno * 20);
    float in2 = inverno * 20;
    float primavera = Float.parseFloat(temperature[2]);
    float pr = 600 - (primavera * 20);
    float pr2 = primavera * 20;
    float estate = Float.parseFloat(temperature[3]);
    float es = 600 - (estate * 20);
    float es2 = estate * 20;
    float autunno = Float.parseFloat(temperature[4]);
    float au = 600 - (autunno * 20);
    float au2 = autunno * 20;


    //Disegno i rettangoli relativi all'inverno
    g.setColor(Color.blue);
    if (inverno < 0) {
     g2d.draw(new Rectangle2D.Float(x, 600, x1, -in2));
    } else {
     g2d.draw(new Rectangle2D.Float(x, in , x1, in2));
    }

    x += 15;

    //Disegno i rettangoli relativi alla primavera
    g.setColor(Color.green);
    g2d.draw(new Rectangle2D.Float(x, pr, x1, pr2));
    //Scrivo i vari anni
    g.setColor(Color.white);
    g.drawString(anno, x, 670);
    x += 15;

    //Disegno i rettangoli relativi all'estate
    g.setColor(Color.red);
    g2d.draw(new Rectangle2D.Float(x, es, x1, es2));
    x += 15;

    //Disegno i rettangoli relativi all'autunno
    g.setColor(Color.orange);
    g2d.draw(new Rectangle2D.Float(x, au, x1, au2));
    x += 23;

   }
   
  } catch (IOException e) {
   System.out.println("IO exception detected");
  }




 }









}
