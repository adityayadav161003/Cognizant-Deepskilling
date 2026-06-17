import java.util.ArrayList;

interface Observer { void update(double price); }
class StockMarket {
    private ArrayList<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void setPrice(double price) { for (Observer o : observers) o.update(price); }
}
