package bd.edu.seu;

import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserved {
    private int id;
    private String source;
    private String destination;
    private String flName;
    private String flCode;
    private LocalDate date;
    private LocalTime time;
    private String cls;
    private String SM_I;
    private int seat;
    private double amount;

    public Reserved(int id, String source, String destination, String flName,
                    String flCode, LocalDate date, LocalTime time, String cls, String SM_I, int seat, double amount) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.flName = flName;
        this.flCode = flCode;
        this.date = date;
        this.time = time;
        this.cls = cls;
        this.SM_I = SM_I;
        this.seat = seat;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlName() {
        return flName;
    }

    public String getFlCode() {
        return flCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getCls() {
        return cls;
    }

    public String getSM_I() {
        return SM_I;
    }

    public int getSeat() {
        return seat;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Reserved{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", flName='" + flName + '\'' +
                ", flCode='" + flCode + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", cls='" + cls + '\'' +
                ", SM_I='" + SM_I + '\'' +
                ", seat=" + seat +
                ", amount=" + amount +
                '}';
    }
}
