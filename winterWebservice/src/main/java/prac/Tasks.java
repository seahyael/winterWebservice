package prac;

import java.time.LocalDateTime;

public class Tasks {
    private long id;
    private String title;
    private boolean done;
    private LocalDateTime created_at;

    public Tasks(){}
    public Tasks(long id, String title, boolean done){
        this.id = id;
        this.title = title;
        this.done = done;
        this.created_at = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString(){
        String str = String.format("%-3s", id)
                + String.format("%20s", title)
                + String.format("  " + "%-5s", done)
                + String.format("  " + "%-20s", created_at);
        return str;
    }

}
