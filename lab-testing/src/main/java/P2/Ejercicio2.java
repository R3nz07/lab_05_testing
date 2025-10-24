package P2;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione algun numero:");
        System.out.println("1. Grafo de Flujo de Control");
        System.out.println("2. Complejidad Ciclomática");
        System.out.println("3. Caminos Independientes");
        System.out.println("4. Casos de Prueba derivados");
        System.out.println("0. Salir");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();
        System.out.println();

        switch (opcion) {
            case 1:
                System.out.println("** Grafo de Flujo de Control **\n");
                System.out.println("1 - Inicio");
                System.out.println("2 - if(intentos >= 3)");
                System.out.println("3 - return 0 (bloqueado)");
                System.out.println("4 - if(usuario == null || password == null)");
                System.out.println("5 - return 0 (datos inválidos)");
                System.out.println("6 - if(usuario.equals(\"admin\") && password.equals(\"admin123\"))");
                System.out.println("7 - nivelAcceso = 3 (admin)");
                System.out.println("8 - else if(password.length() >= 8)");
                System.out.println("9 - nivelAcceso = 2 (normal)");
                System.out.println("10 - nivelAcceso = 1 (restringido)");
                System.out.println("11 - return nivelAcceso");
                break;

            case 2:
                System.out.println("** Complejidad Ciclomática **\n");
                System.out.println("Número de decisiones = 4");
                System.out.println("V(G) = P + 1 = 4 + 1 = 5");
                break;

            case 3:
                System.out.println("** Caminos Independientes **\n");
                System.out.println("C1: 1 → 2 → 3");
                System.out.println("C2: 1 → 2 → 4 → 5");
                System.out.println("C3: 1 → 2 → 4 → 6 → 7 → 11");
                System.out.println("C4: 1 → 2 → 4 → 6 → 8 → 9 → 11");
                System.out.println("C5: 1 → 2 → 4 → 6 → 8 → 10 → 11");
                break;

            case 4:
                System.out.println("** Casos de Prueba derivados **\n");
                System.out.println("Caminos  | usuario       | password      | intentos | Resultado esperado | Descripción");
                System.out.println("Camino 1 | \"test\"      | \"abc12345\"  | 3        | 0                  | Usuario bloqueado");
                System.out.println("Camino 2 | null          | \"abc\"       | 0        | 0                  | Usuario null");
                System.out.println("Camino 3 | \"admin\"     | \"admin123\"  | 0        | 3                  | Usuario administrador");
                System.out.println("Camino 4 | \"user\"      | \"password\"  | 0        | 2                  | Password largo (>=8)");
                System.out.println("Camino 5 | \"user\"      | \"123\"       | 0        | 1                  | Password corto (<8)");
                System.out.println( "C1 → if(intentos >= 3) → return 0\n" +
                                    "C2 → if(intentos < 3) → if(usuario == null || password == null) → return 0\n" +
                                    "C3 → if(intentos < 3) → if(usuario.equals(\"admin\") && password.equals(\"admin123\")) → nivelAcceso=3 → return 3\n" +
                                    "C4 → if(intentos < 3) → if(usuario válido, no admin, password.length>=8) → nivelAcceso=2 → return 2\n" +
                                    "C5 → if(intentos < 3) → if(usuario válido, no admin, password.length<8) → nivelAcceso=1 → return 1\n");

                break;

            case 0:
                System.out.println("** Programa finalizado **");
                break;

            default:
                System.out.println("** Opción inválida. Intente nuevamente **.");
        }

        sc.close();
    }

    public static int validarAccesoUsuario(String usuario, String password, int intentos) {
        int nivelAcceso = 0;
        /** Camino 1: Si el usuario ya intentó 3 veces o más, se bloquea **/
        if (intentos >= 3) {
            System.out.println("Usuario bloqueado");
            return 0;
        }
        /** Camino 2: Si el usuario o contraseña son nulos, se consideran inválidos **/
        if (usuario == null || password == null) {
            return 0;
        }

        /** Camino 3: Si el usuario es 'admin' y la contraseña 'admin123', es administrador **/
        if (usuario.equals("admin") && password.equals("admin123")) {
            nivelAcceso = 3;
        /** Camino C4: Si no es admin, pero la contraseña tiene 8 o más caracteres **/
        } else if (password.length() >= 8) {
            nivelAcceso = 2;
        }
        /** Camino 5: Si la contraseña es menor a 8 caracteres **/
        else {
            nivelAcceso = 1;
        }
        /** Retorna el nivel de acceso determinado **/
        return nivelAcceso;
    }
}
