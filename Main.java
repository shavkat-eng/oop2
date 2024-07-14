import java.util.LinkedList;
import java.util.Queue;

// Интерфейс QueueBehaviour
interface QueueBehaviour {
    void takeInQueue(Human human);
    void releaseFromQueue();
    Human serveNext();
}

// Интерфейс MarketBehaviour
interface MarketBehaviour {
    void acceptOrder(Human human);
    void processOrder(Human human);
}

// Класс Human
class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Класс Market, реализующий интерфейсы QueueBehaviour и MarketBehaviour
class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Human> queue;

    public Market() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void takeInQueue(Human human) {
        queue.add(human);
        System.out.println(human.getName() + " joined the queue.");
    }

    @Override
    public void releaseFromQueue() {
        Human human = queue.poll();
        if (human != null) {
            System.out.println(human.getName() + " left the queue.");
        }
    }

    @Override
    public Human serveNext() {
        return queue.peek();
    }

    @Override
    public void acceptOrder(Human human) {
        System.out.println(human.getName() + "'s order has been accepted.");
    }

    @Override
    public void processOrder(Human human) {
        System.out.println(human.getName() + "'s order is being processed.");
    }

    public void update() {
        Human human = serveNext();
        if (human != null) {
            acceptOrder(human);
            processOrder(human);
            releaseFromQueue();
        }
    }
}

// Класс Main для тестирования
public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        Human human1 = new Human("Alice");
        Human human2 = new Human("Bob");

        market.takeInQueue(human1);
        market.takeInQueue(human2);

        market.update();
        market.update();
    }
}
