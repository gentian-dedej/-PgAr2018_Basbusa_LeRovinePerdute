
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Read
{
	
	/**metodo che legge un file passato come @param filename
	 * e aggiunge ad una mappa,le città che legge,settando i vari attributi
	 * @param cities
	 */
	public void exploreMap(String filename,Map cities) 
	{
		 
		 String attribute=null;
		 int i=-1;
		 int j=0;
		 
		 
			try 
			{
				
				XMLInputFactory xmlif=XMLInputFactory.newInstance();
		        XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
		                                   new FileInputStream(filename));
		        
		        while(xmlr.hasNext()) 
		        {
		        	
		            switch(xmlr.getEventType())
		            {
		            
		            case XMLStreamConstants.START_DOCUMENT:
		            {		            
		            	System.out.println("Start Read Doc "+filename);
		            	
		            	break;
		            }
	        	
	        case XMLStreamConstants.START_ELEMENT:
	        	
	        	attribute=xmlr.getLocalName().trim();
	        	switch (attribute) 
	        	{
	           
				
				case ("city"):
					i++;
				/**
				 * lettura degli attributi della città
				 */
				int id=Integer.parseInt(xmlr.getAttributeValue(0));
				String nome=xmlr.getAttributeValue(1);
				int x=Integer.parseInt(xmlr.getAttributeValue(2));
				int y=Integer.parseInt(xmlr.getAttributeValue(3));
				int h=Integer.parseInt(xmlr.getAttributeValue(4));
				
				cities.cities.add(new City(x, y, h, nome, id));
				
				
				j=0;
				
				
				
					break;
				case ("link"):
					/**
					 * lettura degli attributi di link
					 */
					cities.cities.get(i).citiesId[j]=Integer.parseInt(xmlr.getAttributeValue(0));
				    j++;
				
						break;
	
				default:
					break;
				}
	        	
			          
	        	break;	            	
	       	
	        default:
	        	break;
	        }
	        xmlr.next();
	    }
	   
	   
		        System.out.println("End Read Doc "+filename+"\n");
	}
			catch(Exception e)
			{
				System.err.println("Error detected");
				System.err.println(e.getMessage());
	        }
	}


}
