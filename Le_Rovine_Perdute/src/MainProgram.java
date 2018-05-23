import java.io.File;

import java.io.FilenameFilter;
import java.util.LinkedList;

import it.unibs.fp.mylib.MyMenu;

/** 
 * @author Basbousa Group (Yari Bussi Dedej Gentian)
 */

public class MainProgram {

	public static void main(String[] args) {
		
		
		/**
		 * inizzializzazione degli oggetti utilizzati
		 */
		Read read=new Read();
		Map map=new Map(); 
		Write write=new Write();
		boolean uscita=true;
		String nomeFile=null;
		

		File dir = new File("C:\\Users\\yari7\\OneDrive\\Documenti\\UNIBS\\PRIMO ANNO\\2^_semestre\\Fondamenti di programmazione\\Programmi_Java\\Le_Rovine_Perdute");
		  String[] children = dir.list();
		  
		 
		
		  FilenameFilter filter = new FilenameFilter() {
		      public boolean accept(File dir, String name) {
		          return !name.startsWith("O")&&name.startsWith("P");
		      }
		  };
		  children = dir.list(filter);
		  
	/*	
		int j = 0;
		for (int i=0;i<children.length;i++)
		{
		j = j + 1;
		System.out.println(j + ". " + children[i]);
		}*/
		  
		  MyMenu menu=new MyMenu("quale file vuoi convertire?", children);
		   do
		   {
			   switch (menu.scegli()) 
			   {
			   case 1:
				   nomeFile="PgAr_Map_10000.xml";
				   
				   break;
			   case 2:
				   nomeFile="PgAr_Map_12.xml";
				   
				   break;
			   case 3:
				   nomeFile="PgAr_Map_200.xml";
				   
				   break;
			   case 4:
				   nomeFile="PgAr_Map_2000.xml";
				   
				   break;
			   case 5:
				   nomeFile="PgAr_Map_5.xml";
				   
				   break;
			   case 6:
				   nomeFile="PgAr_Map_50.xml";
				   
				   break;
			   case 0:
				   uscita=false;
			   
			   }
		   
		/**
		 * metodo che legge da un file e crea una mappa di citta
		 */
			   long startTime = System.currentTimeMillis();
		read.exploreMap(nomeFile,map);
		/**
		 * ciclo che setta tutti i vicini di ogni citta
		 */
		for (City c : map.cities)
			c.setAdjacentsCity(map);
		/**
		 * metodi d'appoggio che trovano il campo base e le Rovine Perdute
		 * e li salvano in variabili
		 */
		City rovine=map.findRovinePerdute();
		City campo=map.findCampoBase();
	
		/**
		 * crea un LinkedList di citta contenente il percosro piu breve
		 * dal campo alle Rovine perdute
		 * Il metodo viene ripetuto per entrambi i veicoli
		 */
		map.DijkstraForMetzli(campo);
	    campo.calculateBestPathForTonatiuhTo(rovine);
	    LinkedList<City>tonatiuh=campo.shortestPathTonatiuh;
	    
	    map.DijkstraForTonatiuh(campo);
	    campo.calculateBestPathForMetztliTo(rovine);
	    LinkedList<City>meltzi=campo.shortestPathMetztli;
		
	    /**
	     * metodo che stampa su file secondo
	     * il formato richiesto
	     */
	    write.writeStopTimes(meltzi,tonatiuh,"Output"+nomeFile);
	    map.cities.clear();
	  		
		long endTime   = System.currentTimeMillis();
		System.out.println("Tempo di esecuzione del programma: "+(double)(endTime - startTime)/1000+" secondi");
		   }while (uscita);
			
	}
	
}
