package com.company;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chxbca
 */
public class CommandRunner {


    private static Logger logger = LoggerFactory.getLogger(CommandRunner.class);


    public CommandRunner() {
        this(2);
    }

    public CommandRunner(int threadPoolNum) {
        executorService = new ThreadPoolExecutor(threadPoolNum, threadPoolNum, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(65535),
                new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build());
    }

    private ExecutorService executorService;

    public void run(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            executorService.submit(new GetStream(process.getErrorStream()));
            executorService.submit(new GetStream(process.getInputStream()));
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private static class GetStream implements Runnable {
        private InputStream inputStream;

        GetStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try (BufferedReader bufferedReader =
                         new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    logger.info(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
