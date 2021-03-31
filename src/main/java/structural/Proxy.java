package structural;

import java.util.HashMap;
import java.util.Map;

//* Proxy Pattern
//* Use it when something needs to be done before or after forwarding clients call to service objects.
//* Similar to Facade  : But Proxy implements the same class as its service object.So both proxy and service are used interchangable.
public class Proxy {

    public static void main(String[] args) {

        YouTubeService service = new YouTubeServiceImpl();

        YouTubeService proxy_service = new YouTubeVideoServiceProxy(320);

        proxy_service.addVideo("Helson", "Helson_mashUp.mp4");
        proxy_service.addVideo("Naveena", "Naveena_mashUp.mp4");

        proxy_service.listVideos();

        proxy_service.getVideo("Naveena");


    }
}


interface YouTubeService {

    void listVideos();

    void addVideo(String name, String video);

    void removeVidoe(String name);

    void getVideo(String name);

}

//** Consider this as a third party lib which hides lots of complexities.
class YouTubeServiceImpl implements YouTubeService {

    Map<String, String> videos = new HashMap<String, String>();

    @Override
    public void listVideos() {
        videos.forEach((k, v) -> System.out.println(k + " :" + v));
    }

    @Override
    public void addVideo(String name, String video) {
        videos.put(name, video);
    }

    @Override
    public void removeVidoe(String name) {
        videos.remove(name);
    }

    @Override
    public void getVideo(String name) {
        System.out.println(videos.get(name));
    }

}


//** So we create a proxy class which would implement the same interface as complex class(either extends the complex class or implements the same interface as complex class
class YouTubeVideoServiceProxy extends YouTubeServiceImpl {

    int client_id;

    YouTubeVideoServiceProxy(int client_id) {
        super();
        this.client_id = client_id;
    }

    public boolean checkforAccess() {
        return client_id > 100;
    }

    @Override
    public void addVideo(String name, String video) {
        if (checkforAccess())
            super.videos.put(name, video);
        else
            System.out.println("Invalid client");
    }

    @Override
    public void removeVidoe(String name) {
        if (checkforAccess())
            super.videos.remove(name);
        else
            System.out.println("Invalid client");

    }

    @Override
    public void getVideo(String name) {
        if (checkforAccess())
            super.getVideo(name);

        else
            System.out.println("Invalid client");
    }


}



