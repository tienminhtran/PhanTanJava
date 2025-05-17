package dao.impl;

import dao.ImplDaoCandidates;
import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.*;

public class DaoCandidates implements ImplDaoCandidates {

    final EntityManager em;

    final EntityTransaction et;

    public DaoCandidates() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlytuyendung");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }


    @Override
    public Map<Candidate, Long> listCadidatesByCompanies() {
        String sql = "SELECT c, COUNT(*) AS soCT FROM Experience e JOIN Candidate c ON e.candidate.id = c.id " +
                "GROUP BY c";
        try {
            et.begin();
            List<Object[]> results = em.createQuery(sql).getResultList();
            Map<Candidate, Long> listCandidates = new HashMap<>();

            for (Object[] result : results) {
                Candidate candidate = (Candidate) result[0];
                Long count = (Long) result[1];
                listCandidates.put(candidate, count);
            }
            et.commit();
            return listCandidates;
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCandidate(Candidate candidate) {
        try {
            et.begin();
            if (candidate.getId().matches("^C\\d{3,}$")) {
                em.persist(candidate);
                et.commit();
                return true;
            } else {
                System.out.println("Invalid candidate id");
                return false;
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Candidate, Integer> listYearsOfExperienceByPosition(String candidateID) {
        String sql = "SELECT c, YEAR(e.toDate) - YEAR(e.fromDate) AS total_years_of_experience " +
                "FROM Experience e JOIN Candidate c ON e.candidate.id = c.id where c.id = :candidateID";
        try {
            List<Object[]> results = em.createQuery(sql)
                    .setParameter("candidateID", candidateID)
                    .getResultList();
            Map<Candidate, Integer> map = new HashMap<>();
            for (Object[] result : results) {
                Candidate candidate = (Candidate) result[0];
                Integer yearsOfExperience = (Integer) result[1];
                map.put(candidate, yearsOfExperience);
            }
            return map;

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Candidate, Set<Certificate>> listCadidatesAndCertificates() {
        /*
        select ca.*, ce.*
from candidates ca
join certificates ce
on ca.candidate_id = ce.candidate_id
         */
        String sql = "select ca, ce from Candidate ca join Certificate ce on ca.id = ce.candidate.id";

        Map<Candidate, Set<Certificate>> map = new HashMap<>();
        try {
            List<Object[]> results = em.createQuery(sql).getResultList();
            for (Object[] result : results) {
                Candidate candidate = (Candidate) result[0];
                Certificate certificate = (Certificate) result[1];
                map.computeIfAbsent(candidate, k -> new HashSet<>()).add(certificate);

            }
            return map;


        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() {
//        String sql = "SELECT e.candidate_id, e.position_id, \n" +
//                "       DATEDIFF(year, MIN(e.from_date), MAX(e.to_date)) AS tgLamViec\n" +
//                "FROM experiences e\n" +
//                "JOIN positions p ON e.position_id = p.position_id\n" +
//                "GROUP BY e.candidate_id, e.position_id\n" +
//                "HAVING DATEDIFF(year, MIN(e.from_date), MAX(e.to_date)) >= (\n" +
//                "    SELECT MAX(DATEDIFF(year, from_date, to_date))\n" +
//                "    FROM experiences\n" +
//                "    WHERE candidate_id = e.candidate_id\n" +
//                ")";
      String sql = "SELECT \n" +
              "    e.candidate_id, e.position_id,\n" +
              "    YEAR(e.to_date) - YEAR(e.from_date) AS NAMLAM\n" +
              "FROM \n" +
              "    experiences e\n" +
              "JOIN \n" +
              "    positions p ON e.position_id = p.position_id\n" +
              "JOIN \n" +
              "    candidates c ON e.candidate_id = c.candidate_id\n" +
              "WHERE \n" +
              "    (YEAR(e.to_date) - YEAR(e.from_date)) >= (\n" +
              "        SELECT \n" +
              "            MAX(YEAR(e1.to_date) - YEAR(e1.from_date))\n" +
              "        FROM \n" +
              "            experiences e1\n" +
              "        WHERE \n" +
              "            e1.candidate_id = e.candidate_id\n" +
              "    );";
        Map<Candidate, Position> map = new HashMap<>();
        try {
            List<Object[]> results = em.createNativeQuery(sql).getResultList();
            for (Object[] result : results) {
                Candidate candidate = em.find(Candidate.class, result[0]);
                Position position = em.find(Position.class, result[1]);
                map.put(candidate, position);
            }
            return map;

        }catch(Exception e){
            et.rollback();
            e.printStackTrace();
        }
        return null;
    }


}
