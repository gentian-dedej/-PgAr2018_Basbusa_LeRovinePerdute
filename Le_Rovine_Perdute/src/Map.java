import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

public class Map
{
	/**
	 * una mappa è un linkedlist di città
	 */
	protected LinkedList<City> cities;

	/**
	 * costruttore della mappa
	 */
	public Map()
	{
		cities=new LinkedList<City>();
	}
	
	/**
	 * metodo che riceve come parametro un ID
	 * @param id
	 * e restituisce una citta
	 * @return citta
	 */
	public City findCityById(int id)
	{
		
		
		for (City c : cities)
		{
			if(c.cityId==id)
			{
				return c;
			}
		}
		return null;
	}
	/**
	 * metodo che restituisce l'ID delle rovine perdute
	 * sapendo che è sempre l'ID più alto
	 * @return ID Rovine perdute
	 */
	public int findIdRovinePerdute()
	{
		int maxId=0;
		for(City c: cities)
			if(c.cityId>maxId)
				maxId=c.cityId;
		return maxId;
	}

	/**
	 * metodo che ritorna la città "Rovine Perdute"
	 * cercandola con il suo ID
	 * @return Rovine Perdute
	 */
	public City findRovinePerdute()
	{
		return findCityById(findIdRovinePerdute());
	}
	/**
	 * metodo che ritorna la città con ID=0, cioè il campo base
	 * @return campo base
	 */
	public City findCampoBase()
	{
		return findCityById(0);
	}
	
	/**
	 * metodi che calcolano la distanza tra 2 città 
	 * utilizzando le regole dei due veicoli
	 * 
	 * @param sourceCity
	 * @param arrivalCity
	 * @return distanza tra città
	 */
	public  int CalculateDistanceTeamMetztli(City sourceCity,City arrivalCity) 
	{
		return Math.abs(sourceCity.h-arrivalCity.h);
	}

    public  int CalculateDistanceTeamTonatiuh(City sourceCity,City arrivalCity) 
	{
		return (int)Math.sqrt(Math.pow(sourceCity.x-arrivalCity.x, 2)+
				         Math.pow(sourceCity.y-arrivalCity.y, 2));
	}
	
	
    /**
     * metodo d'appoggio che passato un set di città,
     * restituisce la città avente minore distanza dalla sorgente
     * paragonando gli attributi distanceFromSourceFor* addegnati ai 2 veicoli
     * @param cities
     * @return città con distanza minore
     */
	public City getCityWhitMinDistanceForMetztli(HashSet<City> cities)
	{
		City lowestDistanceCity = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    for (City city: cities)
	    {
	    
	        if (city.distanceFromSourceForMetztli < lowestDistance) {
	            lowestDistance = city.distanceFromSourceForMetztli;
	            lowestDistanceCity = city;
	        }
	    }
	    cities.remove(lowestDistanceCity);
	    return lowestDistanceCity;
	}
	
	public City getCityWhitMinDistanceForTonatiuh(HashSet<City> cities)
	{
		City lowestDistanceCity = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    for (City city: cities)
	    {
	    
	        if (city.distanceFromSourceForTonatiuh < lowestDistance) {
	            lowestDistance = city.distanceFromSourceForTonatiuh;
	            lowestDistanceCity = city;
	        }
	    }
	    cities.remove(lowestDistanceCity);
	    return lowestDistanceCity;
	}
	
/**
 * 	Algoritmi di Dijkstra specifici per le caratteristiche dei 2 veicoli
 */
	
	
	public void DijkstraForMetzli (City source)
	{
		int alt_dist=0;
	
	    City temp=null;
	   
	    HashSet <City> unvisitedCity= new HashSet<City>(); 			  //Q = empty_set


		for(City city: cities)//for each vertex V in G: 
			{
			 	unvisitedCity.add(city);//Q.add(V)
			 	city.distanceFromSourceForMetztli=Integer.MAX_VALUE;
			 	city.prev=null;
			}
		
		source.distanceFromSourceForMetztli=0;							//dist[S] = 0

		
		while(!unvisitedCity.isEmpty())									//while Q is not empty: 
		{
			
			temp=getCityWhitMinDistanceForMetztli(unvisitedCity);		//node T = node in Q with min dist[T]
			
				unvisitedCity.remove(temp);//Q.remove(T)
			
			for (City neighbour:temp.adjacents.cities)					//for each neighbour N of T: 
			{
				if(!unvisitedCity.contains(neighbour))
					continue;
				
				alt_dist = temp.distanceFromSourceForMetztli + CalculateDistanceTeamMetztli(temp, neighbour);
				
				if(alt_dist  <  neighbour.distanceFromSourceForMetztli)
				{
					neighbour.distanceFromSourceForMetztli=alt_dist;
					neighbour.prev=temp;

				}
			}
			
		}
		
	}
	
	public void DijkstraForTonatiuh (City source)
	{
		int alt_dist=0;
	
	    City temp=null;
	   
	    HashSet <City> unvisitedCity= new HashSet<City>();//Q = empty_set


		for(City city: cities)													//for each vertex V in G: 
			{
			 	unvisitedCity.add(city);										//Q.add(V)
			 	city.distanceFromSourceForTonatiuh=Integer.MAX_VALUE;
			 	city.prev=null;
			}
		
		source.distanceFromSourceForTonatiuh=0;									//dist[S] = 0

		
		while(!unvisitedCity.isEmpty())											//while Q is not empty: 
		{
			
			temp=getCityWhitMinDistanceForTonatiuh(unvisitedCity);				//node T = node in Q with min dist[T]
			
			unvisitedCity.remove(temp);											//Q.remove(T)
			
			for (City neighbour:temp.adjacents.cities)							//for each neighbour N of T: 
			{
				if(!unvisitedCity.contains(neighbour))
					continue;
				
				alt_dist = temp.distanceFromSourceForTonatiuh+ CalculateDistanceTeamTonatiuh(temp, neighbour);
				
				if(alt_dist  <  neighbour.distanceFromSourceForTonatiuh)
				{
					neighbour.distanceFromSourceForTonatiuh=alt_dist;
					neighbour.prev=temp;

				}
			}
			
		}
		
	}


}
