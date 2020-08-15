package observer;

public interface Subject {

    void registerObserver(ObserverReset o); // đăng kí

    void removeObserver(ObserverReset o);  // đăng kí

    void notifyObservers();
}
