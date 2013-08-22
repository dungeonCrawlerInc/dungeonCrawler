/*
    * TODO List
        * Figure out how to import Hero.java from the Root directory, instead of copying the file to this package
 */

//Note: This website was really helpful: "http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/"

package characterLoader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
//TODO: Import Hero.java

public class CharacterLoader {
    public CharacterLoader(String heroName)
    {
        /*
        try {
            File heroFile = new File("C:\\Users\\Grehg\\IdeaProjects\\epicCrawl\\savedCharacters\\Sample Hero.char");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(heroFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("hero");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("race: " + eElement.getElementsByTagName("race").item(0).getTextContent());
                    System.out.println("strength: " + eElement.getElementsByTagName("strength").item(0).getTextContent());
                    System.out.println("dexterity: " + eElement.getElementsByTagName("dexterity").item(0).getTextContent());
                    System.out.println("intelligence: " + eElement.getElementsByTagName("intelligence").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       */
    }

    public static void main(String[] args) {
        //CharacterLoader characterDesigner = new CharacterLoader("Sample Hero.char");    //TODO: Un-hardcode the heroName
    } //main
}   //Class CharacterLoader