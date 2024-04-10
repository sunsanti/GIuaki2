package midterm;

import java.io.IOException;
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
public class AgecCalculatorThread implements Runnable {

	private BlockingQueue<String> dateOfBirthQueue;
    private BlockingQueue<String> encodedAgeQueue;
    private volatile boolean stop;

    AgecCalculatorThread(BlockingQueue<String> dateOfBirthQueue, BlockingQueue<String> encodedAgeQueue) {
        this.dateOfBirthQueue = dateOfBirthQueue;
        this.encodedAgeQueue = encodedAgeQueue;
        this.stop = false;
    }

    @Override
    public void run() {
        try {
            while (!stop) {
                String dateOfBirth = dateOfBirthQueue.take();
                if (dateOfBirth.equals("stop")) {
                    stop = true;
                    break;
                }
                // Tính tuổi và mã hóa chữ số
                // (Cài đặt tính tuổi và mã hóa ở đây)
                String encodedAge = ""; // Chuỗi đã mã hóa tuổi
                encodedAgeQueue.put(encodedAge);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopProcessing() {
        this.stop = true;
    }
}
