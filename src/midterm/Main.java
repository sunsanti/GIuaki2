package midterm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main  {
	public static void main(String[] args) {
		BlockingQueue<String> dateOfBirthQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> encodedAgeQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Boolean> primeCheckResultQueue = new LinkedBlockingQueue<>();

        Thread readerThread = new Thread(new FileReaderThread("students.xml", dateOfBirthQueue));
        Thread ageCalculatorThread = new Thread(new AgecCalculatorThread(dateOfBirthQueue, encodedAgeQueue));
        Thread primeCheckerThread = new Thread(new PrimeCheckerThread(encodedAgeQueue, primeCheckResultQueue));

        readerThread.start();
        ageCalculatorThread.start();
        primeCheckerThread.start();

        try {
            readerThread.join();
            ageCalculatorThread.join();
            primeCheckerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Nhận dữ liệu từ primeCheckerThread và xử lý
        boolean allPrime = true;
        while (!primeCheckResultQueue.isEmpty()) {
            Boolean isPrime = primeCheckResultQueue.poll();
            if (isPrime == null || !isPrime) {
                allPrime = false;
                break;
            }
        }

        if (allPrime) {
            System.out.println("Tổng các chữ số trong ngày sinh là số nguyên tố.");
        } else {
            System.out.println("Tổng các chữ số trong ngày sinh không phải là số nguyên tố.");
        }
    }
	}

