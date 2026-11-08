package starter;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    List<Subscriber> listSubscriber = new ArrayList<>();

    public void addSubscriber(Subscriber sub) {
        listSubscriber.add(sub);
    }

    public void removeSubscriber(Subscriber sub) {
        listSubscriber.remove(sub);
    }

    public void notifySubscriber() {
        for (Subscriber sub : listSubscriber) {
            sub.update();
        }
    }
}