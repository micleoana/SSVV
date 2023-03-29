package ssvv.example;

import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class ServiceTest {
    Service service;
    @Before
    public void setUp(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "src/main/java/fisiere/Studenti.xml";
        String filenameTema = "src/main/java/fisiere/Teme.xml";
        String filenameNota = "src/main/java/fisiere/Note.xml";


        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addAssignmentValidIDTest(){
        Tema validTema = new Tema("1","Tema test",2,2);
        Tema t = service.addTema(validTema);
        assertNotNull(t);
    }

    @Test
    public void addAssignmentInvalidIDTest(){
        Tema invalidTema = new Tema(null,"Tema test",2,2);
        assertThrows(ValidationException.class, ()-> service.addTema(invalidTema));
    }

}
