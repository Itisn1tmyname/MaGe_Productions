package mage.karteikartensimulator.Datenmodell;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //TODO: Testing Main, delete this when not needed anymore!
        String profilJSON = """
                {
                     "name": "standard",
                     "istAktivesProfil": true,
                     "stats": [
                        {
                           "id": "ID-1",
                           "evaluation": 0,
                           "dateLastChecked": "2026.04.22 14:09",
                           "dateLastRichtig": "2026.04.22 14:09"
                        },
                        {
                           "id": "ID-2",
                           "evaluation": 0,
                           "dateLastChecked": "2026.04.22 14:09",
                           "dateLastRichtig": "2026.04.22 14:09"
                        },
                        {
                           "id": "ID-3",
                           "evaluation": 0,
                           "dateLastChecked": "2026.04.22 14:09",
                           "dateLastRichtig": "2026.04.22 14:09"
                        }
                     ]
                }""";
        try {
            Profil profil = new Profil(profilJSON);
            System.out.println(profil);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
