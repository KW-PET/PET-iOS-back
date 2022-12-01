package com.kw.pet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseMapping {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class PostandComm{
        private Object post;
        private Object comment;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class UserandPet{
        private Object user;
        private Object pet;
    }
}
