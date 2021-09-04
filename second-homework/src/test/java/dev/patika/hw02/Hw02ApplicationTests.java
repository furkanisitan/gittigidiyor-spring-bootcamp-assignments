package dev.patika.hw02;

import dev.patika.hw02.dataaccess.abstracts.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Hw02ApplicationTests {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void contextLoads() {


    }

}
