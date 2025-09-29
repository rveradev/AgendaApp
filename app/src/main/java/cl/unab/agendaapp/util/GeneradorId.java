package cl.unab.agendaapp.util;

import java.util.UUID;

public class GeneradorId {
    public static String obtenerId() {
        return UUID.randomUUID().toString();
    }
}
