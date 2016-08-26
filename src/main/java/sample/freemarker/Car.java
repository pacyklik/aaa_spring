package sample.freemarker;

public class Car {

    public Car() {
    }

    public Car(String numerRej, String silnik) {
        this.numerRej = numerRej;
        this.silnik = silnik;
    }

    private String numerRej;
    private String silnik;

    public String getNumerRej() {
        return numerRej;
    }

    public String getSilnik() {
        return silnik;
    }

    public void setNumerRej(String numerRej) {
        this.numerRej = numerRej;
    }

    public void setSilnik(String silnik) {
        this.silnik = silnik;
    }
}
