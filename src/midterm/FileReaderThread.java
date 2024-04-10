package midterm;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileReaderThread implements Runnable {
    private String filename;
    private BlockingQueue<String> dateOfBirthQueue;

    FileReaderThread(String filename, BlockingQueue<String> dateOfBirthQueue) {
        this.filename = filename;
        this.dateOfBirthQueue = dateOfBirthQueue;
    }

    @Override
    public void run() {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String dateOfBirth = element.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                if (isValidDateOfBirth(dateOfBirth)) {
                    dateOfBirthQueue.put(dateOfBirth);
                } else {
                    System.err.println("Invalid date of birth detected: " + dateOfBirth);
                }
            }

            dateOfBirthQueue.put("stop");
        } catch (IOException | ParserConfigurationException | SAXException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidDateOfBirth(String dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateOfBirth);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
