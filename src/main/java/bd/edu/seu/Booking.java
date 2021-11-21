package bd.edu.seu;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private String source, destination, Fname, Fcode, Cls, SM_I;
    private LocalDate date;
    private LocalTime time;

    public Booking(String source, String destination, String fname,
                   String fcode, LocalDate date, LocalTime time, String cls, String SM_I) {
        this.source = source;
        this.destination = destination;
        Fname = fname;
        Fcode = fcode;
        Cls = cls;
        this.SM_I = SM_I;
        this.date = date;
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getFname() {
        return Fname;
    }

    public String getFcode() {
        return Fcode;
    }

    public String getCls() {
        return Cls;
    }

    public String getSM_I() {
        return SM_I;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", Fname='" + Fname + '\'' +
                ", Fcode='" + Fcode + '\'' +
                ", Cls='" + Cls + '\'' +
                ", SM_I='" + SM_I + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
