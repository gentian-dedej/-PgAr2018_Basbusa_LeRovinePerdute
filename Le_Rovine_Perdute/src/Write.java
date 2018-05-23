import java.io.FileOutputStream;
import java.util.LinkedList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Write 
{
	
	public boolean writeStopTimes(LinkedList<City> shorterPathMetztli, LinkedList<City> shorterPathTonatiuh,String filename)
	{
		System.out.printf("Scrittura sul file %s.........\n",filename);
		
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try 
		{
			/**
			 * scrittura del percorso per i due team secondo
			 * la formattazione richiesta
			 */
			writer = output.createXMLStreamWriter(new FileOutputStream(filename),"UTF-8");
			writer.writeStartDocument("UTF-8","1.0");
			writer.writeComment("data saved");
			
			writer.writeCharacters( "\n");//serve per andare a capo
			writer.writeStartElement("solution");
			writer.writeCharacters( "\n");//serve per andare a capo
				writer.writeStartElement("route");//start route
				
					writer.writeAttribute("Team", "Metztli");
					writer.writeAttribute("cost", ""+shorterPathMetztli.getFirst().distanceFromSourceForMetztli);
					writer.writeAttribute("cities", ""+shorterPathMetztli.size());
		for(int i = shorterPathMetztli.size()-1;i>=0;i--)
		{			
			writer.writeCharacters( "\n");
				writer.writeEmptyElement("city");// Start city
					writer.writeAttribute("id",shorterPathMetztli.get(i).cityId+"");
					writer.writeAttribute("name",""+shorterPathMetztli.get(i).cityName);
		}
		writer.writeCharacters( "\n");
				writer.writeEndElement(); // End routes

				writer.writeCharacters( "\n");
				
				writer.writeStartElement("route");//start route
				
					writer.writeAttribute("Team", "Tonatiuh");
					writer.writeAttribute("cost", ""+shorterPathTonatiuh.getFirst().distanceFromSourceForTonatiuh);
					writer.writeAttribute("cities", ""+shorterPathTonatiuh.size());
		for(int i = shorterPathTonatiuh.size()-1;i>=0;i--)
		{			
			writer.writeCharacters( "\n");
				writer.writeEmptyElement("city");// Start city
					writer.writeAttribute("id",shorterPathTonatiuh.get(i).cityId+"");
					writer.writeAttribute("name",""+shorterPathTonatiuh.get(i).cityName);
		}
		writer.writeCharacters( "\n");
				writer.writeEndElement(); // End routes
		writer.writeCharacters( "\n");//serve per andare a capo
				writer.writeEndElement(); // End solution
				writer.writeEndDocument(); //End Document
				writer.flush();
				writer.close();
		
		System.out.printf("Scrittura sul file %s avvenuta con successo\n",filename);
		System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
}
	
}
//writeEmptyElement