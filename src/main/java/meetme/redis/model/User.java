package meetme.redis.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {

    private String id;
    private String name;
    private Timestamp lastSeen;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
