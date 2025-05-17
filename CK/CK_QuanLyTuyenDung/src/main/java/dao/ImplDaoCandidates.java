package dao;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.util.Map;
import java.util.Set;

public interface ImplDaoCandidates {
    public Map<Candidate, Long> listCadidatesByCompanies();

    public boolean addCandidate(Candidate candidate);

    public Map<Candidate, Integer> listYearsOfExperienceByPosition(String candidateId);

    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates();

    public Map<Candidate, Position> listCandidatesWithLongestWorking();
}
