package meetme.redis.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import meetme.configuration.LocalDateDeserializer;
import meetme.configuration.LocalDateSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("User")
public class User {

    @Id
    private String userId;
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate lastSeen;


    private String gender;
    private String sexualOrientation;
    private String profileTextDescription;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDate lastSeen) {
        this.lastSeen = lastSeen;
    }
}
