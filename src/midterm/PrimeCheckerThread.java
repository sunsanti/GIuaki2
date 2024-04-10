package midterm;
import java.io.File;
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
public class PrimeCheckerThread implements Runnable{
	private BlockingQueue<String> encodedAgeQueue;
    private BlockingQueue<Boolean> primeCheckResultQueue;
    private volatile boolean stop;

    PrimeCheckerThread(BlockingQueue<String> encodedAgeQueue, BlockingQueue<Boolean> primeCheckResultQueue) {
        this.encodedAgeQueue = encodedAgeQueue;
        this.primeCheckResultQueue = primeCheckResultQueue;
        this.stop = false;
    }

    @Override
    public void run() {
        try {
            while (!stop) {
                String encodedAge = encodedAgeQueue.take();
                if (encodedAge.equals("stop")) {
                    stop = true;
                    break;
                }
                // Kiểm tra tổng các chữ số có phải là số nguyên tố
                // (Cài đặt kiểm tra ở đây)
                boolean isPrime = false; // Kết quả kiểm tra
                primeCheckResultQueue.put(isPrime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopProcessing() {
        this.stop = true;
    }
    }
