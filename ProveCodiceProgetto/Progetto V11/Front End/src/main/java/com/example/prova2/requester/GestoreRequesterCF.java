package com.example.prova2.requester;
import java.io.IOException;
import java.util.List;

import static com.example.prova2.requester.GestoreRequesterMail.getAllGestore;

public class GestoreRequesterCF {
    private static String url = "http://localhost:9094/gestore";

    public static List<String> getAllCfGestore() throws IOException, InterruptedException {
        return getAllGestore(url+"/cf");
    }
}
