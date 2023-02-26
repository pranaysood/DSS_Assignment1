package org.example.controller;


import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@WebServlet(urlPatterns = "longtask", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private BlockingQueue<AsyncContext> acs = new LinkedBlockingQueue<>();
    private final Executor executor = Executors.newFixedThreadPool(4);

    @Override
    public void init() throws ServletException {
        new Thread(() -> {
            while (true) {
                try {
                    AsyncContext context = acs.take();

                    executor.execute(new MyService(context));
                } catch (InterruptedException e) {
                    log(e.getMessage());
                }
            }
        }).start();
        
        
        
        
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        resp.getWriter().println("Starting long task servlet");
        
        final long startTime = System.nanoTime();

        AsyncContext ac = req.startAsync();

        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                log("ASYNC complete");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                log("ASYNC timout");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                log("ASYNC error" + event.getThrowable());
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            	log("ASYNC timout");

            }
        });

        acs.add(ac);
        
       
        resp.getWriter().println("AsyncServlet Work completed. Time elapsed: " + (System.nanoTime() - startTime));
	   

        log("Ended serving in doGet");
    }
}

class MyService implements Runnable {
    private AsyncContext ac;

    public MyService(AsyncContext ac) {
        this.ac = ac;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = ac.getResponse().getWriter();
            writer.println("Starting long task");
            Thread.sleep(1000 * 5);
            writer.println("Ended long task");
            ac.complete();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}