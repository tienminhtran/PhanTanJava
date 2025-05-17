package test;

import dao.impl.DaoCandidates;
import entity.Candidate;
import entity.Position;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DaoTestCandidates {

    private DaoCandidates daoCandidates;

    @BeforeAll
    public void setUp() {
        daoCandidates = new DaoCandidates();
    }

    @Test
    public void testListCadidatesByCompanies() {
        daoCandidates.listCadidatesByCompanies().forEach((k,v)-> System.out.println(k+" "+v));
    }


    @Test
    public void testAdd() {
        Candidate candidate = new Candidate();
        candidate.setId("C202");
        candidate.setFullName("tien");
        candidate.setEmail("tien@gmail.com");
        candidate.setDescription("software engineer");
        candidate.setGender("Male");
        candidate.setPhone("123-456-7890");
        candidate.setYearOfBirth(1999);
        Position position = new Position();
        position.setId("P101");
        candidate.setPosition(position);
        assert(daoCandidates.addCandidate(candidate));
    }

    @Test
    public void testAddFalse() {
        Candidate candidate = new Candidate();
        candidate.setId("202");
        candidate.setFullName("tien");
        candidate.setEmail("tien@gmail.com");
        candidate.setDescription("software engineer");
        candidate.setGender("Male");
        candidate.setPhone("123-456-7890");
        candidate.setYearOfBirth(1999);
        Position position = new Position();
        position.setId("P101");
        candidate.setPosition(position);
        assertFalse(daoCandidates.addCandidate(candidate));
    }

    @Test
    public void listYearsOfExperienceByPosition() {
        daoCandidates.listYearsOfExperienceByPosition("C101").forEach((k,v)-> System.out.println(k+" "+v));

    }
    @Test
    public void listCadidatesAndCertificates() {
        daoCandidates.listCadidatesAndCertificates().forEach((k,v)-> System.out.println(k+" "+v));
    }

    @Test
    public void listCandidatesWithLongestWorking() {
        daoCandidates.listCandidatesWithLongestWorking().forEach((k,v)-> System.out.println(k+" "+v));
    }

    @AfterAll
    public void tearDown() {
        daoCandidates = null;
    }

}
