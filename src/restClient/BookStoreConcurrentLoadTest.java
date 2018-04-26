package restClient;

import bean.BookBean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BookStoreConcurrentLoadTest {

    private static class Result {
        long timeReturned;
        int status;
        long latency;

        public Result(long timeReturned, int status, long latency) {
            this.timeReturned = timeReturned;
            this.status = status;
            this.latency = latency;
        }

    }

    private static class LoadTestRunner {
        int maxConcurrent;
        AtomicInteger inFlight;
        ConcurrentLinkedQueue<Optional<Result>> results;
        ConcurrentLinkedQueue<Client> clients;
        ExecutorService executor;

        public LoadTestRunner(int maxConcurrent) {
            this.maxConcurrent = maxConcurrent;
            this.inFlight = new AtomicInteger(0);
            this.results = new ConcurrentLinkedQueue<>();
            this.executor = Executors.newFixedThreadPool(maxConcurrent);
            this.clients = new ConcurrentLinkedQueue<>();
            for (int i = 0; i < maxConcurrent; i++) {
                this.clients.add(ClientBuilder.newClient());
            }
        }

        private void submit(String url) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    inFlight.incrementAndGet();
                    long startTime = System.currentTimeMillis();
                    Client client = clients.poll();
                    try {
                        client.target(url)
                                .request(MediaType.APPLICATION_XML)
                                .get(BookBean.class);
                        long endTime = System.currentTimeMillis();
                        results.add(Optional.of(new Result(endTime, 200, endTime - startTime)));
                    } catch (Exception e) {
                        results.add(Optional.empty());
                        e.printStackTrace();
                    } finally {
                        inFlight.decrementAndGet();
                        clients.add(client);
                    }
                }
            });
        }

        private void shutDownAll(){
            System.out.println("Shutting down all clients");
            clients.forEach(Client::close);
            executor.shutdown();
            System.out.println("Successfully shut down");
        }

        public ConcurrentLinkedQueue<Optional<Result>> run(String url) {
            for (int i = 0; i < maxConcurrent; i++) {
                submit(url);
            }
            System.out.println("Waiting on remaining requests");
            //Wait until all requests are served
            while (inFlight.get() != 0) {
                try {
                    Thread.sleep(3000);
                    System.out.println("Still in flight: " + inFlight.get());
                } catch (InterruptedException e) {

                }

            }
            return results;
        }


    }

    public static void main(String[] args) throws IOException {
        int maxConcurrent = 100;
        LoadTestRunner runner = new LoadTestRunner(maxConcurrent);

        long start = System.currentTimeMillis();
        ConcurrentLinkedQueue<Optional<Result>> results =
                runner.run("http://localhost:8080/bookStore/getProductInfoTest?bookId=b001");
        long stop = System.currentTimeMillis();
        long totalTimeSeconds = (stop - start)/1000;

        System.out.println("Total test time " + totalTimeSeconds + " seconds");
        System.out.println("Total requests run " + results.size());
        LinkedList<Result> outList = new LinkedList<>();
        for (Optional<Result> res : results) {
            res.ifPresent(outList::add);
        }
        LinkedList<String> out = new LinkedList<>();
        out.add("Status, Time Returned, Latency");
        for(Result s : outList){
            out.add(s.status + "," + s.timeReturned + "," + s.latency);
        }
        System.out.println("Successful " + outList.size());
        Path outFile = Paths.get("/home/jose/latency_results_concurent.csv");
        outFile.toFile().createNewFile();
        try {
            Files.write(outFile, out, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        runner.shutDownAll();

    }

}
