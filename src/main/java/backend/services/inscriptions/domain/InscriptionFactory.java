package backend.services.inscriptions.domain;

public class InscriptionFactory {
    public Inscription createInscription(String course, String section, String period) {
        return new Inscription(course, section, period);
    }
}
