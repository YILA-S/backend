package backend.services.inscriptions.infra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("inscription")
public class InscriptionModel {
    @Id
    public InscriptionIdentification id;
    public ArrayList<String> students;
    public String teacher;
}
