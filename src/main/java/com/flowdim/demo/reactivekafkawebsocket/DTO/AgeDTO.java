package com.flowdim.demo.reactivekafkawebsocket.DTO;

import lombok.AllArgsConstructor;


public class AgeDTO {
    private long id;

    public AgeDTO(long id, long count) {
        this.id = id;
        this.count = count;
    }

    @Override
    public String toString() {
        return "AgeDTO{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private long count;
}
