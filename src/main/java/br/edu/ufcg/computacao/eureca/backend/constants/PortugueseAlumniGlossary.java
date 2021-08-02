package br.edu.ufcg.computacao.eureca.backend.constants;

public class PortugueseAlumniGlossary implements Glossary {

    public AlumniGlossaryFields getGlossary() {
        Field alumniTracked = new Field("Egressos rastreados","Egressos que já tiveram seus perfis no LinkedIn encontrados");
        Field consolidatedEmployers = new Field("Empresas consolidadas","Empresas que já tiveram seus perfis no LinkedIn encontrados");
        Field employersInAcademy = new Field("Na academia","Egressos na academia");
        Field employersInGovernment = new Field("No governo","Egressos no governo");
        Field employersInOngs = new Field(" Em ONGs","Egressos em ONGs");
        Field employersInIndustry = new Field("Na indústria","Egressos na indústria");

        return new AlumniGlossaryFields(alumniTracked, consolidatedEmployers, employersInAcademy, employersInGovernment,
                employersInOngs, employersInIndustry);
    }
}
