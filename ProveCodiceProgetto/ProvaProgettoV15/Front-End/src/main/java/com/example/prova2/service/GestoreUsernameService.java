package com.example.prova2.service;
import java.io.IOException;
import java.util.List;
import static com.example.prova2.service.GestoreMailService.getAllGestore;

public class GestoreUsernameService {
    private static String url = "http://localhost:9094/gestore";
    public static List<String> getAllUsernameGestore() throws IOException, InterruptedException {
        return getAllGestore(url+"/username");
    }
}
