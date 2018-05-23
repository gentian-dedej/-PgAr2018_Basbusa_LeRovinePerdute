import java.util.LinkedList;

/** 
 * @author Basbousa Group (Yari Bussi Dedej Gentian)
 */

public class MainProgram {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		/**
		 * inizzializzazione degli oggetti utilizzati
		 */
		Read read=new Read();
		Map map=new Map(); 
		Write write=new Write();
		
		/**
		 * metodo che legge da un file e crea una mappa di citta
		 */
	
		read.exploreMap("PgAr_Map_50.xml",map);
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
	    write.writeStopTimes(meltzi,tonatiuh,"Output_Map_50.xml");
	    
	  		
		long endTime   = System.currentTimeMillis();
		System.out.println("Tempo di esecuzione del programma: "+(double)(endTime - startTime)/1000+" secondi");
		
	}
	
}
